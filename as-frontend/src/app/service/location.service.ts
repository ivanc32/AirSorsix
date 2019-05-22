import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError, tap } from 'rxjs/operators';
import { HttpHeaders } from '@angular/common/http';
import { Location } from 'src/model/Location';


const api = 'http://localhost:8080/api';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    'Authorization': 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class LocationService {

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

  postLocation(location: Location) {
    return this.postObject<Location>(`${api}/create/location`, location);
  }

  searchOrigin(query: string): Observable<Location[]> {
    return this.getObject<Location[]>(`${api}/origin/?origin=${query}`);
  }

  searchDestinations(depAirport: string, query: string): Observable<Location[]> {
    return this.getObject<Location[]>(`${api}/destinations/?origin=${depAirport}&destination=${query}`);
  }

  getLocation(depAirport: string): Observable<Location> {
    return this.getObject<Location>(`${api}/location/?airport=${depAirport}`);
  }


}
