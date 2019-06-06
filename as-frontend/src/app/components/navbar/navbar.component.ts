import { Component, OnInit } from '@angular/core';
import { AuthGuard } from 'src/app/service/auth-guard.service';
import { AuthenticationService } from 'src/app/service/authentication.service';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(private authenticationService: AuthenticationService) {}

  isAuthenticated = false;

  ngOnInit() {
    this.authenticationService.isLoggedIn().subscribe(it => this.isAuthenticated = it);
  }

}
