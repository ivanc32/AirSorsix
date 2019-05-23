import {Component, OnInit} from '@angular/core';
import {FormGroup, FormControl, Validators, FormBuilder} from '@angular/forms';
import {RegisterService} from 'src/app/service/register.service';
import {User} from 'src/model/User';

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

  constructor(private formBuilder: FormBuilder, private registerService: RegisterService) {
  }

  ngOnInit() {
    this.registrationForm = this.formBuilder.group({
      user: ['', Validators.required],
      password: ['', Validators.required],
      confirmPassword: ['', Validators.required]
    });
  }

  register() {
    this.registerService.registerUser({
      username: this.registrationForm.get('user').value,
      password: this.registrationForm.get('password').value,
      role: 'USER'
    } as User).subscribe(user =>
      console.log(user)
    );
  }

}
