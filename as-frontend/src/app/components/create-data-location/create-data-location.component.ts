import { Component, OnInit } from '@angular/core';
import { LocationService } from '../../service/location.service';
import { FormControl, FormGroup, FormBuilder, Validators } from '@angular/forms';


@Component({
  selector: 'app-create-data-location',
  templateUrl: './create-data-location.component.html',
  styleUrls: ['./create-data-location.component.css']
})
export class CreateDataLocationComponent implements OnInit {

  createLocationForm = new FormGroup({
  city: new FormControl(''),
  country: new FormControl(''),
  airport: new FormControl(''),
  price: new FormControl(''),
  });

  constructor(private locationService: LocationService,
              private formBuilder: FormBuilder) { }

  ngOnInit() {
    this.createLocationForm = this.formBuilder.group({
      city: ['', Validators.required],
      country: ['', Validators.required],
      airport: ['', Validators.required],
      price: ['', Validators.required]
  });
  }

  onSubmit() {
    this.locationService.postLocation(this.createLocationForm.value)
    .subscribe(location => console.log(location));
  }

}
