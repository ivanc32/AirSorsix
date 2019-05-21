import { NgModule } from '@angular/core';
import { Route, RouterModule } from '@angular/router';
import { CreateDataComponent } from '../components/create-data/create-data.component';
import { LoginComponent } from '../components/login/login.component';
import { ReserveFlightComponent } from '../components/reserve-flight/reserve-flight.component';
import { SearchDetailsComponent } from '../components/search-details/search-details.component';
import { HomePageComponent } from '../components/home-page/home-page.component';

const routes: Route[] = [{
  path: 'search?origin=:origin&destination=:destination&datefrom=:dateFrom&dateto=:dateto',
  component: SearchDetailsComponent
}, {
  path: 'search',
  component: SearchDetailsComponent
}, {
  path: '',
  redirectTo: 'home',
  pathMatch: 'full'
  }, {
  path: 'home',
  component: HomePageComponent
  }, {
  path: 'create',
  component: CreateDataComponent
  }, {
  path: 'test',
  component: LoginComponent
  }, {
  path: 'reserve',
  component: ReserveFlightComponent
  }, {
  path: 'reserve?flight=:flight',
  component: ReserveFlightComponent
  }];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})

export class AppRoutingModule { }
