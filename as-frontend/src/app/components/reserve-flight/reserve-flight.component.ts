import {Component, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {map} from 'rxjs/operators';
import { ReservationService } from 'src/app/service/reservation.service';
import { Reservation } from 'src/model/Reservation';

@Component({
  selector: 'app-reserve-flight',
  templateUrl: './reserve-flight.component.html',
  styleUrls: ['./reserve-flight.component.css']
})
export class ReserveFlightComponent implements OnInit {

  @Output() flight: string;
  @Output() returnFlight: string;
  flightEconomySeats = 0;
  flightBusinessSeats = 0;
  returnFlightEconomySeats = 0;
  returnFlightBusinessSeats = 0;

  constructor(private route: ActivatedRoute, private reservationService: ReservationService, private router: Router) {
  }

  ngOnInit() {
    this.route.queryParamMap.pipe(
      map(params => params.getAll('flight'))
    ).subscribe(params => {
      this.flight = params[0];
      this.returnFlight = params[1];
      console.log(params);
    });
  }

  onPress() {
    if (this.flightEconomySeats !== 0 || this.flightBusinessSeats !== 0) { // post for the going flight
      this.reservationService.postReservation({ flightId: this.flight, userId: 'test',
                                                economyTickets: this.flightEconomySeats,
                                                businessTickets: this.flightBusinessSeats} as Reservation);
    }

    if (this.returnFlightEconomySeats !== 0 || this.returnFlightBusinessSeats !== 0) { // post for the return flight
      this.reservationService.postReservation({ flightId: this.returnFlight, userId: 'test',
                                                economyTickets: this.returnFlightEconomySeats,
                                                businessTickets: this.returnFlightBusinessSeats} as Reservation);
    }

    this.router.navigateByUrl('/home');
  }

}
