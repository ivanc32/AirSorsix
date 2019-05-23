import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Plane } from 'src/model/Plane';

const api = '/api';


@Injectable({
  providedIn: 'root'
})
export class PlaneService {


  constructor(private http: HttpClient) { }

  getObject<T>(url: string): Observable<T[]> {
    return this.http.get<T[]>(url)
      .pipe(
        catchError(this.handleError([]))
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

  postPlane(plane: Plane) {
    return this.postObject<Plane>(`${api}/create/plane`, plane);
  }
}
