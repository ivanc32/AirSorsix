import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Location } from 'src/model/Location';


const api = '/api';


@Injectable({
  providedIn: 'root'
})
export class LocationService {

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

  postLocation(location: Location) {
    return this.postObject<Location>(`${api}/create/location`, location);
  }

  searchOrigin(query: string): Observable<Location[]> {
    return this.http.get<Location[]>(`${api}/origin/?origin=${query}`);
  }

  searchDestinations(depAirport: string, query: string): Observable<Location[]> {
    return this.http.get<Location[]>(`${api}/destinations/?origin=${depAirport}&destination=${query}`);
  }

  getLocation(depAirport: string): Observable<Location> {
    return this.http.get<Location>(`${api}/location/?airport=${depAirport}`);
  }


}
