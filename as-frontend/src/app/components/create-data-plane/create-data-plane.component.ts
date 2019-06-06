import { Component, OnInit } from '@angular/core';
import { PlaneService } from '../../service/plane.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-create-data-plane',
  templateUrl: './create-data-plane.component.html',
  styleUrls: ['./create-data-plane.component.css']
})
export class CreateDataPlaneComponent implements OnInit {

  createPlaneForm = new FormGroup({
    manufacturer: new FormControl(''),
    model: new FormControl(''),
    numberOfEconomySeats: new FormControl(''),
    priceOfEconomySeat: new FormControl(''),
    numberOfBusinessSeats: new FormControl(''),
    priceOfBusinessSeat: new FormControl('')
  });

  constructor(private planeService: PlaneService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createPlaneForm = this.formBuilder.group({
      manufacturer: ['', Validators.required],
      model: ['', Validators.required],
      numberOfEconomySeats: ['', Validators.required],
      priceOfEconomySeat: ['', Validators.required],
      numberOfBusinessSeats: ['', Validators.required],
      priceOfBusinessSeat: ['', Validators.required]
  });
  }

  onSubmit() {
    this.planeService.postPlane(this.createPlaneForm.value)
    .subscribe(plane => console.log(plane));
  }
}
