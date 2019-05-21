import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import { User } from 'src/model/User';
import { AuthenticationService } from '../../service/authentication.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  isAuthenticated = false;
  user = new User();
  errorMessage: string;

  constructor(private authenticationService: AuthenticationService, private router: Router) {
  }

  ngOnInit() {
  }

  logIn() {
    this.authenticationService.logIn(this.user).subscribe(
      response => {
        this.router.navigate(['/homeComponent']);
      }
    );

  }


}
