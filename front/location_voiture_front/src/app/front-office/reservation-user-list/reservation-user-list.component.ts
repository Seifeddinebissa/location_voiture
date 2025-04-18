import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { Reservation } from '../../front-office/models/reservation.model';

@Component({
  selector: 'app-reservation-user-list',
  templateUrl: './reservation-user-list.component.html',
  styleUrls: ['./reservation-user-list.component.css'],
})
export class ReservationUserListComponent implements OnInit {
  reservations: Reservation[] = [];

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getAllReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
      },
      (error) => {
        console.error('Erreur lors du chargement des réservations:', error);
      }
    );
  }
}