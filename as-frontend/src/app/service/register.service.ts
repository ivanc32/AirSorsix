import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { User } from 'src/model/User';

const api = '/api';

@Injectable({
  providedIn: 'root'
})

export class RegisterService {

  constructor(private http: HttpClient) { }

  getObject<T>(url: string): Observable<T> {
    return this.http.get<T>(url)
      .pipe(
        catchError(this.handleError(null))
      );
  }

  postObject<T>(url: string, object: T): Observable<T> {
    return this.http.post<T>(url, object)
      .pipe(
        catchError(this.handleError(object))
      );
  }

  handleError<T>(element: T) {
    return (err: any) => {
      console.log('error occured', err);
      return of(element);
    };
  }

  registerUser(user: User) {
    return this.postObject<User>(`${api}/user/create/reservation`, user);
  }
}
