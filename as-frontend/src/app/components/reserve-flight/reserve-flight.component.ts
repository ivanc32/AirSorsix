import {Component, OnInit, Output} from '@angular/core';
import {ActivatedRoute} from '@angular/router';
import {map} from 'rxjs/operators';

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

  constructor(private route: ActivatedRoute) {
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
    console.log(this.returnFlightEconomySeats);
    console.log(this.flightEconomySeats, this.flightBusinessSeats, this.returnFlightEconomySeats, this.returnFlightBusinessSeats);
  }

}
