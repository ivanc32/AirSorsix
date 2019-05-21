import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable} from 'rxjs';
import { User } from 'src/model/User';
import { map } from 'rxjs/operators';
import { LoginResponse } from 'src/model/LoginResponse';

@Injectable()
export class AuthenticationService {

  isAuthenticated = Observable.create();
  user: Observable<{}>;
  redirectUrl: string;

  constructor(private http: HttpClient) {
  }

  getUser(): Observable<{}> {
    if (this.user == null) {
      this.user = this.http.get('localhost:8080/api/user');
      return this.user;
    }
  }

  public logIn(user: User) {
    const base64Credential: string = btoa(user.username + ':' + user.password);
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: `Basic ${base64Credential}`
    });
    const options = {headers};
    return this.http.get('/api/login', options)
    .pipe(
        map((response: LoginResponse) => {
          const loggedInUser = response.principal;
          if (loggedInUser) {
            localStorage.setItem('currentUser', JSON.stringify(loggedInUser));
            //console.log(localStorage.getItem('currentUser'));
          } else {
            console.log('BELJAA');
          }
        })
      );
  }


}
