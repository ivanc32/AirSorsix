import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { User } from 'src/model/User';
import { AuthenticationService } from '../../service/authentication.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { CookieService } from 'ngx-cookie-service';
import { HttpRequest } from '@angular/common/http';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  userLogged: boolean;
  loginForm = new FormGroup({
    username: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private authenticationService: AuthenticationService, private router: Router, private formBuilder: FormBuilder,
    private cookie: CookieService, ) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  onSubmit() {
    console.log('LoginComponent#onSubmit');

    this.authenticationService.login(this.loginForm.get('username').value,
                                     this.loginForm.get('password').value)
                                     .subscribe({
                                      next: () => window.location.href = '/home',
                                      error: error => console.log(`Error occurred: ${error.message}`)
                                      });
                                    }


}
