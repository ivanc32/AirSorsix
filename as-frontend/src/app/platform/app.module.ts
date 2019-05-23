import { CreateDataComponent } from '../components/create-data/create-data.component';
import { CreateDataLocationComponent } from '../components/create-data-location/create-data-location.component';
import { CreateDataPlaneComponent } from '../components/create-data-plane/create-data-plane.component';
import { CreateDataFlightComponent } from '../components/create-data-flight/create-data-flight.component';
import { AuthenticationService } from '../service/authentication.service';
import { AuthGuard } from '../service/auth-guard.service';
import { LoginComponent } from '../components/login/login.component';
import { ReserveFlightComponent } from '../components/reserve-flight/reserve-flight.component';
import { FlightInfoComponent } from '../components/flight-info/flight-info.component';
import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbDatepickerModule} from '@ng-bootstrap/ng-bootstrap';
import {AppRoutingModule} from './app-routing.module';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {MatCardModule} from '@angular/material';
import { AppComponent } from '../app.component';
import { HomePageComponent } from '../components/home-page/home-page.component';
import { NavbarComponent } from '../components/navbar/navbar.component';
import { FlightSearchComponent } from '../components/flight-search/flight-search.component';
import { SearchDetailsComponent } from '../components/search-details/search-details.component';

@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent,
    FlightSearchComponent,
    SearchDetailsComponent,
    CreateDataComponent,
    CreateDataLocationComponent,
    CreateDataPlaneComponent,
    CreateDataFlightComponent,
    LoginComponent,
    ReserveFlightComponent,
    FlightInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    FormsModule,
    NgbDatepickerModule,
    MatCardModule
  ],
  providers: [
    AuthenticationService,
    AuthGuard
],
  bootstrap: [AppComponent]
})
export class AppModule { }
