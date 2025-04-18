import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { BackOfficeRoutingModule } from './back-office-routing.module';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ReservationListComponent } from './dashboard/reservation-list/reservation-list.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    DashboardComponent,
    ReservationListComponent
  ],
  imports: [
    CommonModule,
    BackOfficeRoutingModule,
    HttpClientModule,
    FormsModule
  ]
})
export class BackOfficeModule { }
