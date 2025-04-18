import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { Reservation } from '../models/reservation.model';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-ajout-reservation',
  templateUrl: './ajout-reservation.component.html',
  styleUrls: ['./ajout-reservation.component.css'],
})
export class AjoutReservationComponent implements OnInit {
  reservation: Reservation = {
    nom: '', // Ajout du champ nom
    start_date: '',
    end_date: '',
    status: '',
  };
  isEditMode = false;
  dateError = false;
  successMessage: string | null = null;
  errorMessage: string | null = null;

  constructor(
    private reservationService: ReservationService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.queryParamMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.reservationService.getReservationById(+id).subscribe(
        (data: Reservation) => {
          this.reservation = data;
          this.validateDates();
        },
        (error) => {
          this.errorMessage = 'Erreur lors de la récupération de la réservation : ' + error.message;
          console.error('Erreur lors de la récupération de la réservation:', error);
        }
      );
    }
  }

  validateDates(): void {
    if (this.reservation.start_date && this.reservation.end_date) {
      const startDate = new Date(this.reservation.start_date);
      const endDate = new Date(this.reservation.end_date);
      this.dateError = endDate <= startDate;
    } else {
      this.dateError = false;
    }
  }

  saveReservation(): void {
    this.successMessage = null;
    this.errorMessage = null;

    console.log('Données envoyées :', this.reservation);

    if (this.isEditMode) {
      this.reservationService.updateReservation(this.reservation).subscribe(
        () => {
          this.successMessage = 'Réservation mise à jour avec succès !';
          setTimeout(() => {
            this.router.navigate(['/front-office/reservations']);
          }, 2000);
        },
        (error) => {
          this.errorMessage = 'Erreur lors de la mise à jour de la réservation : ' + error.message;
          console.error('Erreur lors de la mise à jour de la réservation:', error);
        }
      );
    } else {
      this.reservationService.addReservation(this.reservation).subscribe(
        () => {
          this.successMessage = 'Réservation ajoutée avec succès !';
          setTimeout(() => {
            this.router.navigate(['/front-office/reservations']);
          }, 2000);
        },
        (error) => {
          this.errorMessage = 'Erreur lors de l\'ajout de la réservation : ' + error.message;
          console.error('Erreur lors de l\'ajout de la réservation:', error);
        }
      );
    }
  }

  cancel(): void {
    this.router.navigate(['/front-office/reservations']);
  }
}