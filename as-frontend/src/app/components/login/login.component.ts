import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { AuthenticationService } from '../../service/authentication.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private authenticationService: AuthenticationService,
              private router: Router,
              private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  onSubmit() {
    this.authenticationService.login(this.loginForm.get('username').value,
                                     this.loginForm.get('password').value)
                                     .subscribe({
                                      next: () => window.location.href = '/home',
                                      error: error => console.log(`Error occurred: ${error.message}`)
                                      });
                                    }

}
