import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {Observable, of} from 'rxjs';
import { User } from 'src/model/User';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';

const httpOptions = {
  headers: new HttpHeaders({
      'Content-Type': 'application/json',
  })
};


@Injectable()
export class AuthenticationService {
  isAuthenticated = Observable.create();
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
              this.isAuthenticated = true;
              return value;
          }),
          catchError(() => {
              this.isAuthenticated = false;
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

  getUser(): Observable<User> {
      if (this.user == null) {
          this.user = this.http.get('/api/user').pipe(
                map(value => {
                  this.isAuthenticated = true;
                  return value;
              }),
              catchError(() => {
                  this.isAuthenticated = false;
                  return of(null);
              }));
      }
      return this.user;
  }

}
