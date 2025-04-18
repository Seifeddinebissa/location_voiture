import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ReservationListComponent } from './dashboard/reservation-list/reservation-list.component';
import { BackOfficeComponent } from './back-office.component';
const routes: Routes = [
  {
    path: '',
    component: BackOfficeComponent,
    children: [
      { path: 'reservations', component: ReservationListComponent },

      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      },
      {
        path: 'dashboard',
        component: DashboardComponent
      },
    ]

  },
  
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class BackOfficeRoutingModule { }
