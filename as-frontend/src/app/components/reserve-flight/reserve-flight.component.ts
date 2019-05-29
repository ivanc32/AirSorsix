import {Component, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {map} from 'rxjs/operators';
import {ReservationService} from 'src/app/service/reservation.service';
import {Reservation} from 'src/model/Reservation';
import {UserService} from 'src/app/service/user.service';
import {forkJoin} from 'rxjs';

@Component({
  selector: 'app-reserve-flight',
  templateUrl: './reserve-flight.component.html',
  styleUrls: ['./reserve-flight.component.css']
})
export class ReserveFlightComponent implements OnInit {

  @Output() flight: string;
  @Output() returnFlight: string;
  userId: string;
  flightEconomySeats = 0;
  flightBusinessSeats = 0;
  returnFlightEconomySeats = 0;
  returnFlightBusinessSeats = 0;

  constructor(private route: ActivatedRoute, private reservationService: ReservationService
    , private router: Router, private userService: UserService) {
  }

  ngOnInit() {
    this.route.queryParamMap.pipe(
      map(params => params.getAll('flight')
      )).subscribe(params => {
      this.flight = params[0];
      this.returnFlight = params[1];
      console.log(params);
    });
    this.userService.getUser().subscribe(user => this.userId = user['principal']['id']);
  }

  onPress() {
    if (this.flightEconomySeats !== 0 || this.flightBusinessSeats !== 0) { // post for the going flight
      this.reservationService.postReservation({
        flightId: this.flight, userId: this.userId,
        economyTickets: this.flightEconomySeats,
        businessTickets: this.flightBusinessSeats
      })
        .subscribe(reservation =>
          console.log(reservation)
        );
    }

    if (this.returnFlightEconomySeats !== 0 || this.returnFlightBusinessSeats !== 0) { // post for the return flight
      this.reservationService.postReservation({
        flightId: this.returnFlight, userId: this.userId,
        economyTickets: this.returnFlightEconomySeats,
        businessTickets: this.returnFlightBusinessSeats
      }).subscribe(reservation =>
        console.log(reservation)
      );
    }

    this.router.navigateByUrl('/home');
  }

}
