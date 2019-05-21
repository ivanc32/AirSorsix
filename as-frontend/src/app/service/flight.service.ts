import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Flight} from '../domain/Flight';

@Injectable({
  providedIn: 'root'
})
export class FlightService {

  api = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  getFlights(depAirport: string, arrAirport: string): Observable<Flight[]> {
    return this.http.get<Flight[]>(`${this.api}/api/flights/${depAirport}/${arrAirport}`);
  }
}
