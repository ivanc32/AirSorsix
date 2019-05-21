import { Component, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { map } from 'rxjs/operators';

@Component({
  selector: 'app-reserve-flight',
  templateUrl: './reserve-flight.component.html',
  styleUrls: ['./reserve-flight.component.css']
})
export class ReserveFlightComponent implements OnInit {

  @Output() flight: string;
  @Output() returnFlight: string;

  constructor(private route: ActivatedRoute) { }

  ngOnInit() {
    this.route.queryParamMap.pipe(
      map(params => params.getAll('flight'))
    ).subscribe(params => {
      this.flight = params[0];
      this.returnFlight = params[1];
      console.log(params);
    });
  }
}
