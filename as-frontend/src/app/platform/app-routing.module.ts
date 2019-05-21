import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {HomePageComponent} from '../components/home-page/home-page.component';
import {SearchDetailsComponent} from '../components/search-details/search-details.component';

const routes: Routes = [
  {path: 'search?origin=:origin&destination=:destination&datefrom=:dateFrom&dateto=:dateto', component: SearchDetailsComponent},
  {path: 'search', component: SearchDetailsComponent},
  {path: '', redirectTo: 'home', pathMatch: 'full'},
  {path: 'home', component: HomePageComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
