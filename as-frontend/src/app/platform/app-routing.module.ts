import {NgModule} from '@angular/core';
import {Route, RouterModule} from '@angular/router';
import {CreateDataComponent} from '../components/create-data/create-data.component';
import {LoginComponent} from '../components/login/login.component';
import {ReserveFlightComponent} from '../components/reserve-flight/reserve-flight.component';
import {SearchDetailsComponent} from '../components/search-details/search-details.component';
import {HomePageComponent} from '../components/home-page/home-page.component';
import { RegisterComponent } from '../components/register/register.component';
import { AuthGuard } from '../service/auth-guard.service';
import { CanActivate } from '@angular/router/src/utils/preactivation';

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
  component: CreateDataComponent,
  canActivate: [AuthGuard]
}, {
  path: 'login',
  component: LoginComponent
}, {
  path: 'register',
  component: RegisterComponent
}, {
  path: 'reserve?flight=:flight',
  component: ReserveFlightComponent,
  canActivate: [AuthGuard]
}, {
  path: 'reserve',
  component: ReserveFlightComponent,
  canActivate: [AuthGuard]
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

export class AppRoutingModule {
}
