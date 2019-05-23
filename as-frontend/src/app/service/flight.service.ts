import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Flight } from 'src/model/Flight';

const api = '/api';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

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

  postFlight(flight: Flight) {
    return this.postObject<Flight>(`${api}/create/flight`, flight);
  }

  getFlightById(id: string): Observable<Flight> {
    return this.getObject<Flight>(`${api}/flights/${id}`);
  }

  getFlights(depAirport: string, arrAirport: string): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${api}/flights/${depAirport}/${arrAirport}`);
  }
}
