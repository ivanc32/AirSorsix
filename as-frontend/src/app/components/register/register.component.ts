import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {RegisterService} from 'src/app/service/register.service';
import {User} from 'src/model/User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  registrationForm = new FormGroup({
    user: new FormControl(''),
    password: new FormControl(''),
    confirmPassword: new FormControl('')
  });

  constructor(private formBuilder: FormBuilder,
              private registerService: RegisterService,
              private router: Router) {
  }

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      user: ['', Validators.compose([Validators.minLength(7), Validators.maxLength(20)])],
      password: ['', Validators.compose([Validators.minLength(7), Validators.maxLength(30)])],
      confirmPassword: ['']
    }, {validator: this.checkPasswords});
  }

  checkPasswords(group: FormGroup) {
  return group.controls.password.value === group.controls.confirmPassword.value ? null : { notSame: true };
  }

  register() {
    this.registerService.registerUser({
      username: this.registrationForm.get('user').value,
      password: this.registrationForm.get('password').value,
      role: 'ROLE_USER'} as User).subscribe();
  }

}
