import {Component, OnInit, Input, Output, EventEmitter} from '@angular/core';
import {Flight} from 'src/model/Flight';
import {FlightService} from '../../service/flight.service';
import {ReservationService} from '../../service/reservation.service';
import {forkJoin} from 'rxjs';
import {Reservation} from 'src/model/Reservation';

@Component({
  selector: 'app-flight-info',
  templateUrl: './flight-info.component.html',
  styleUrls: ['./flight-info.component.css']
})
export class FlightInfoComponent implements OnInit {

  @Input() flightId: string;
  flight: Flight;
  reservations: Reservation[]; // redundant
  takenEconomySeats: number;
  takenBusinessSeats: number;
  priceOfEconomyTicket: number;
  economyTicketsToReserve = 0;
  businessTicketsToReserve = 0;
  @Output() emitEconomyTickets = new EventEmitter<number>();
  @Output() emitBusinessTickets = new EventEmitter<number>();

  constructor(private flightService: FlightService, private reservationService: ReservationService) {
  }

  ngOnInit() {
    forkJoin(
      this.flightService.getFlightById(`${this.flightId}`),
      this.reservationService.getReservationsByFlight(`${this.flightId}`))
      .subscribe(([flight, reservations]) => {
        this.flight = flight;
        console.log(this.flight);
        this.reservations = reservations;
        console.log(this.reservations);
        this.takenBusinessSeats = this.reservations.reduce((sum, item) => sum + item.businessTickets, 0);
        this.takenEconomySeats = this.reservations.reduce((sum, item) => sum + item.economyTickets, 0);
      });

  }

  changeBusinessPlusMinusToggle(update: number) {
    this.businessTicketsToReserve += update;

    if (this.businessTicketsToReserve < 0) {
      this.businessTicketsToReserve = 0;
    }

    if (this.businessTicketsToReserve > this.flight.plane.businessSeats - this.takenBusinessSeats) {
      this.businessTicketsToReserve = this.flight.plane.businessSeats - this.takenBusinessSeats;
    }
    this.emitBusinessTickets.emit(this.businessTicketsToReserve);
  }

  changeEconomyPlusMinusToggle(update: number) {
    this.economyTicketsToReserve += update;

    if (this.economyTicketsToReserve < 0) {
      this.economyTicketsToReserve = 0;
    }

    if (this.economyTicketsToReserve > this.flight.plane.economySeats - this.takenEconomySeats) {
      this.economyTicketsToReserve = this.flight.plane.economySeats - this.takenEconomySeats;
    }
    this.emitEconomyTickets.emit(this.economyTicketsToReserve);
  }

}
