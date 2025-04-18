import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { FrontOfficeComponent } from './front-office.component';
import { HomeComponent } from './home/home.component';
import { AjoutReservationComponent } from './ajout-reservation/ajout-reservation.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';

const routes: Routes = [
  {
    path: '',
    component: FrontOfficeComponent,
    children: [
      {
        path: '',
        redirectTo: 'home',
        pathMatch: 'full'
      },
      
      {
        path: 'home',
        component: HomeComponent
      },
      { path: 'ajout-reservation', component: AjoutReservationComponent },
      { path: 'reservations', component: ReservationListComponent },
    ]
    
    
  }
];

    

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FrontOfficeRoutingModule { }
