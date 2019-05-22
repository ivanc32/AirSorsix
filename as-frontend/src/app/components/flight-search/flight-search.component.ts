import {Component, OnInit} from '@angular/core';
import {Subject} from 'rxjs';
import {distinctUntilChanged, switchMap} from 'rxjs/operators';
import {FormBuilder, FormControl, FormGroup} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {NgbDate, NgbDateStruct} from '@ng-bootstrap/ng-bootstrap';
import {Flight} from 'src/model/Flight';
import {Location} from 'src/model/Location';
import {LocationService} from '../../service/location.service';
import {FlightService} from '../../service/flight.service';


@Component({
  selector: 'app-flight-search',
  templateUrl: './flight-search.component.html',
  styleUrls: ['./flight-search.component.css']
})
export class FlightSearchComponent implements OnInit {

  daFlag = true;
  resultsFlag = false;
  twoWay = true;
  checked = false;
  submitButton = true;
  depLocations: Location[];
  arrLocations: Location[];
  chosenDepLocation: Location;
  chosenArrLocation: Location;
  flights: Flight[];
  returnFlights: Flight[];
  private searchOrigin$ = new Subject<string>();
  private searchDest$ = new Subject<string>();
  flightSearchFormGroup = new FormGroup({
    origin: new FormControl(''),
    destination: new FormControl(''),
    dateFrom: new FormControl(''),
    dateTo: new FormControl('')
  });
  isDisabled = (date: NgbDate) => !this.hasFlight(date, this.flights);
  isDisabledTwoWay = (date: NgbDate) => !this.hasFlight(date, this.returnFlights) || !this.dateIsAfter(date, this.flightSearchFormGroup.get('dateFrom').value);


  constructor(private locationService: LocationService, private activetedRoute: ActivatedRoute,
              private router: Router, private flightService: FlightService, private formBuilder: FormBuilder) {
  }

  ngOnInit() {
    this.flightSearchFormGroup = this.formBuilder.group({
      origin: [''],
      destination: [''],
      dateFrom: [''],
      dateTo: ['']
    });


    this.searchOrigin$.pipe(
      distinctUntilChanged(),
      switchMap(query => this.locationService.searchOrigin(query))
    ).subscribe(locations => {
        this.depLocations = locations;
      }
    );


    this.searchDest$.pipe(
      switchMap(query => {
        if (this.chosenDepLocation != null) {
          return this.locationService.searchDestinations(this.chosenDepLocation.airport, query);
        } else {
          return this.locationService.searchDestinations(' ', query);
        }
      })
    ).subscribe(locations => {
      this.arrLocations = locations;
    });

    this.flightSearchFormGroup.get('dateFrom').valueChanges.subscribe(value => {
      this.flightSearchFormGroup.get('dateTo').setValue('');
      this.checkIfValid();
    });

    this.flightSearchFormGroup.get('dateTo').valueChanges.subscribe(value =>
      this.checkIfValid()
    );

    this.checkIfValid();
  }

  searchOrigin(origin: string) {
    this.searchOrigin$.next(origin);
  }

  searchDestination(destination: string) {
    this.searchDest$.next(destination);
  }

  onOriginClick(location: Location) {
    this.chosenDepLocation = location;
    this.flightSearchFormGroup.get('origin').setValue(location.city);
  }

  onDestinationClick(location: Location) {
    this.chosenArrLocation = location;
    this.flightSearchFormGroup.get('destination').setValue(location.city);
    this.getFlights();
  }

  onOriginChange(query: string) {
    this.searchOrigin$.next(query);
    if (this.chosenDepLocation == null) {
      return;
    }
    if (this.flightSearchFormGroup.get('origin').value.toLocaleLowerCase() !== this.chosenDepLocation.city.toLocaleLowerCase()) {
      this.chosenDepLocation = null;
      this.chosenArrLocation = null;
      this.flightSearchFormGroup.get('destination').setValue('');
      this.deleteFlights();
    }
  }

  onDestinationChange(query: string) {
    this.searchDest$.next(query);
    if (this.chosenArrLocation == null) {
      return;
    }
    if (this.flightSearchFormGroup.get('destination').value.toLocaleLowerCase() !== this.chosenArrLocation.city.toLocaleLowerCase()) {
      this.chosenArrLocation = null;
      this.deleteFlights();
    }
  }

  changeFlag(flag: boolean, value: string = '') {
    this.daFlag = flag;
    this.resultsFlag = true;
    if (flag) {
      this.searchOrigin(value);
    } else {
      this.searchDestination(value);
    }
  }

  checkIfValid() {
    if (this.chosenDepLocation != null && this.chosenArrLocation != null) {
      if (!this.twoWay) {
        this.submitButton = this.flightSearchFormGroup.get('dateFrom').value === '' || this.flightSearchFormGroup.get('dateTo').value === '';
      } else {
        this.submitButton = this.flightSearchFormGroup.get('dateFrom').value === '';
      }
    } else {
      this.submitButton = true;
    }
  }

  onSubmit() {
    const datefrom = this.flightSearchFormGroup.get('dateFrom').value;
    if (this.twoWay) {
      this.router.navigateByUrl(`search?origin=${this.chosenDepLocation.airport}&destination=${this.chosenArrLocation.airport}&datefrom=${datefrom.year}%20${datefrom.month}%20${datefrom.day}`);
    } else {
      const dateto = this.flightSearchFormGroup.get('dateTo').value;
      this.router.navigateByUrl(`search?origin=${this.chosenDepLocation.airport}&destination=${this.chosenArrLocation.airport}&datefrom=${datefrom.year}%20${datefrom.month}%20${datefrom.day}&dateto=${dateto.year}%20${dateto.month}%20${dateto.day}`);
    }
  }

  dateIsAfter(date: NgbDate, flightDate: NgbDateStruct): boolean {
    const calendarDate = new Date(date.year, date.month - 1, date.day);
    const checkDate = new Date(flightDate.year, flightDate.month - 1, flightDate.day);
    return calendarDate > checkDate;
  }

  hasFlight(date: NgbDate, flights: Flight[]): boolean {
    if (flights !== undefined && flights !== null) {
      let flag = false;
      const checkDate = new Date(date.year, date.month - 1, date.day);
      flights.forEach(flight => {
        const newDate = new Date(flight.departureDateTime);
        if (checkDate.toDateString() === newDate.toDateString()) {
          flag = true;
        }
      });
      return flag;
    }
    return false;
  }

  deleteFlights() {
    this.flights = null;
    this.twoWay = true;
    this.checked = false;
    this.flightSearchFormGroup.get('dateFrom').setValue(null);
    this.flightSearchFormGroup.get('dateTo').setValue(null);

  }

  getFlights() {
    this.flightService.getFlights(this.chosenDepLocation.airport, this.chosenArrLocation.airport).subscribe(flights => {
      this.flights = flights;
    });

    this.flightService.getFlights(this.chosenArrLocation.airport, this.chosenDepLocation.airport).subscribe(flights => {
      this.returnFlights = flights;
    });
  }

  twoWayClick() {
    this.twoWay = !this.twoWay;
    this.checked = !this.checked;
    this.flightSearchFormGroup.get('dateTo').setValue('');
    this.checkIfValid();
  }

}
