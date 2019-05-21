import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {NgbDatepickerModule} from '@ng-bootstrap/ng-bootstrap';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from '../app.component';
import {HomePageComponent} from '../components/home-page/home-page.component';
import {NavbarComponent} from '../components/home-page/navbar/navbar.component';
import {FlightSearchComponent} from '../components/home-page/flight-search/flight-search.component';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {SearchDetailsComponent} from '../components/search-details/search-details.component';
import {MatCardModule} from '@angular/material';


@NgModule({
  declarations: [
    AppComponent,
    HomePageComponent,
    NavbarComponent,
    FlightSearchComponent,
    SearchDetailsComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    NgbDatepickerModule,
    MatCardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
