import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {forkJoin, Observable, of} from 'rxjs';
import {Location} from '../domain/Location';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  api = 'http://localhost:8080';

  constructor(private http: HttpClient) {
  }

  searchOrigin(query: string): Observable<Location[]> {
    return this.http.get<Location[]>(`${this.api}/api/origin/?origin=${query}`);
  }

  searchDestinations(depAirport: string, query: string): Observable<Location[]> {
    return this.http.get<Location[]>(`${this.api}/api/destinations/?origin=${depAirport}&destination=${query}`);
  }

  getLocation(depAirport: string): Observable<Location> {
    return this.http.get<Location>(`${this.api}/api/location/?airport=${depAirport}`);
  }
}
