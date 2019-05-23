import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { User } from 'src/model/User';
import { AuthenticationService } from '../../service/authentication.service';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isAuthenticated = false;
  user = new User();
  errorMessage: string;

  loginForm = new FormGroup({
    user: new FormControl(''),
    password: new FormControl('')
  });

  constructor(private authenticationService: AuthenticationService, private router: Router, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      user: ['', Validators.required],
      password: ['', Validators.required]
  });
  }

  logIn() {
    this.authenticationService.logIn(this.user).subscribe(
      response => {
        this.router.navigate(['/home']);
      }
    );

  }

}
