import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { User } from 'src/model/User';
import { catchError, map } from 'rxjs/operators';


@Injectable()
export class AuthenticationService {
  isAuthenticated = false;
  user: Observable<User>;
  redirectUrl: string;

  constructor(private http: HttpClient) {}

  login(username: string, password: string): Observable<User> {
      const credentials = btoa(username + ':' + password);
      const headers = new HttpHeaders({
          'Content-Type': 'application/json',
          Authorization: `Basic ${credentials}`,
          'X-Requested-With': 'XMLHttpRequest'
      });

      return this.http.get<User>('/api/login', {headers}).pipe(
          map(value => {
              return value;
          }),
          catchError(() => {
              return of(null);
          })
      );
  }

  logout(): Observable<boolean> {
      return this.http.get<boolean>('/api/logout').pipe(
          map(value => {
              this.isAuthenticated = false;
              return value;
          })
      );
  }

  isLoggedIn(): Observable<boolean> {
          return this.http.get('/api/user/logged').pipe(
                map(value => {
                  console.log("test");
                  this.isAuthenticated = true;
                  return value;
              }),
              catchError(() => {
                  console.log("error");
                  this.isAuthenticated = false;
                  return of(null);
              }));
  }

}
