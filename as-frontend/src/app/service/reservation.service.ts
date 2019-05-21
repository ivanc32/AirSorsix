import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, of } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { Reservation } from 'src/model/Reservation';


const api = 'http://localhost:8080/api';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {

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

  postReservation(reservation: Reservation) {
    return this.postObject<Reservation>(`${api}/user/create/reservation`, reservation);
  }

  getReservationsByFlight(flightId: string): Observable<Reservation[]> {
    return this.getObject<Reservation[]>(`${api}/reservations/${flightId}`);
  }
}
