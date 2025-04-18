import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { FrontOfficeRoutingModule } from './front-office-routing.module';
import { HomeComponent } from './home/home.component';
import { AjoutReservationComponent } from './ajout-reservation/ajout-reservation.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { ReservationUserListComponent } from './reservation-user-list/reservation-user-list.component';
import { ReservationListComponent } from './reservation-list/reservation-list.component';


@NgModule({
  declarations: [
    HomeComponent,
    AjoutReservationComponent,
    ReservationUserListComponent,
    ReservationListComponent
  ],
  imports: [
    CommonModule,
    FrontOfficeRoutingModule,
    HttpClientModule,
      FormsModule,
      ReactiveFormsModule
       
  ]
})
export class FrontOfficeModule { }
