import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, FormBuilder, Validators } from '@angular/forms';
import { FlightService } from '../../service/flight.service';

@Component({
  selector: 'app-create-data-flight',
  templateUrl: './create-data-flight.component.html',
  styleUrls: ['./create-data-flight.component.css']
})
export class CreateDataFlightComponent implements OnInit {

  createFlightForm = new FormGroup({
    plane: new FormControl(''),
    code: new FormControl(''),
    departureDateTime: new FormControl(''),
    arrivalDateTime: new FormControl(''),
    departureLocation: new FormControl(''),
    arrivalLocation: new FormControl(''),
    businessSeats: new FormControl(''),
    economySeats: new FormControl('')
  });

  constructor(private flightService: FlightService,
    private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createFlightForm = this.formBuilder.group({
      plane: ['', Validators.required],
      code: ['', Validators.required],
      departureDateTime: ['', Validators.required],
      arrivalDateTime: ['', Validators.required],
      departureLocation: ['', Validators.required],
      arrivalLocation: ['', Validators.required],
      businessSeats: ['', Validators.required],
      economySeats: ['', Validators.required]
    });
  }

  onSubmit() {
    this.flightService.postFlight(this.createFlightForm.value)
    .subscribe(flight => console.log(flight));
  }
}
