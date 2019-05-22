import {Component, OnChanges, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {LocationService} from '../../service/location.service';
import {FlightService} from '../../service/flight.service';
import {forkJoin, Observable} from 'rxjs';
import {Flight} from 'src/model/Flight';
import {Location} from 'src/model/Location';

@Component({
  selector: 'app-search-details',
  templateUrl: './search-details.component.html',
  styleUrls: ['./search-details.component.css']
})
export class SearchDetailsComponent implements OnInit {


  flights: Flight[];
  returnFlights: Flight[];
  originLocation: Location;
  destinationLocation: Location;
  dateF: Date;
  dateT: Date;
  origin = '';
  destination = '';
  dateFrom: string[];
  dateTo: string[];
  chosenGoFlight: Flight;
  chosenReturnFlight: Flight;

  constructor(private activatedRoute: ActivatedRoute, private locationService: LocationService,
              private flightService: FlightService, private router: Router) {
  }

  ngOnInit() {

    this.activatedRoute.queryParamMap.subscribe(url => {
      this.origin = url.get('origin');
      this.destination = url.get('destination');
      this.dateFrom = url.get('datefrom').split(' ');
      this.dateF = new Date(parseInt(this.dateFrom[0], 10), parseInt(this.dateFrom[1], 10) - 1, parseInt(this.dateFrom[2], 10));
      if (url.get('dateto') != null) {
        this.dateTo = url.get('dateto').split(' ');
        this.dateT = new Date(parseInt(this.dateTo[0], 10), parseInt(this.dateTo[1], 10) - 1, parseInt(this.dateTo[2], 10));
      } else {
        this.returnFlights = null;
      }
      this.getLocations();
    });

  }

  getLocations() {
    forkJoin(this.locationService.getLocation(this.origin), this.locationService.getLocation(this.destination))
      .subscribe(([one, two]: Location[]) => {
        this.originLocation = one;
        this.destinationLocation = two;
        if (this.originLocation != null && this.destinationLocation != null) {
          this.getFlights();
        }
      });
  }

  getFlights() {
    this.flightService.getFlights(this.originLocation.airport, this.destinationLocation.airport).subscribe(flights => {
      this.flights = flights.filter(flight => {
        const newDate = new Date(flight.departureDateTime);
        if (newDate.toDateString() === this.dateF.toDateString()) {
          return true;
        }
        return false;
      });
    });
    if (this.dateT != null) {
      this.flightService.getFlights(this.destinationLocation.airport, this.originLocation.airport).subscribe(flights => {
        this.returnFlights = flights.filter(flight => {
          const newDate = new Date(flight.departureDateTime);
          if (newDate.toDateString() === this.dateT.toDateString()) {
            return true;
          }
          return false;
        });
      });
    }
  }

  goToReservation(goToFlight: Flight, returnFlight: Flight) {
    if (returnFlight == null) {
      this.router.navigateByUrl(`/reserve?flight=${goToFlight.id}`);
    } else {
      this.router.navigateByUrl(`/reserve?flight=${goToFlight.id}&flight=${returnFlight.id}`);
    }
  }
}

