import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ReservationService } from '../../services/reservation.service';
import { Reservation } from '../../front-office/models/reservation.model';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css'],
})
export class ReservationListComponent implements OnInit {
  reservations: Reservation[] = [];
  successMessage: string | null = null;
  errorMessage: string | null = null;

  // Formulaire pour la modification
  reservationForm: FormGroup;
  isEditing: boolean = false;
  editingReservation: Reservation | null = null;

  constructor(
    private reservationService: ReservationService,
    private fb: FormBuilder
  ) {
    this.reservationForm = this.fb.group(
      {
        nom: ['', Validators.required],
        start_date: ['', Validators.required],
        end_date: ['', Validators.required]
      },
      { validators: this.dateValidator.bind(this) }
    );
  }

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getAllReservations().subscribe({
      next: (reservations: Reservation[]) => {
        this.reservations = reservations.map(res => ({
          ...res,
          start_date: res.start_date || '',
          end_date: res.end_date || '',
          status: res.status || ''
        }));
      },
      error: (error) => {
        this.errorMessage = 'Erreur lors du chargement des réservations : ' + (error.message || 'Une erreur est survenue');
        console.error('Erreur lors du chargement des réservations:', error);
      }
    });
  }

  dateValidator(control: AbstractControl): { [key: string]: boolean } | null {
    const startDate = control.get('start_date')?.value;
    const endDate = control.get('end_date')?.value;

    if (!startDate || !endDate) {
      return null;
    }

    const start = new Date(startDate);
    const end = new Date(endDate);

    if (isNaN(start.getTime()) || isNaN(end.getTime())) {
      return { invalidDateFormat: true };
    }

    return end > start ? null : { invalidDates: true };
  }

  onSubmit(): void {
    if (this.reservationForm.invalid) {
      this.errorMessage = 'Veuillez remplir tous les champs requis.';
      this.reservationForm.markAllAsTouched();
      return;
    }

    if (this.reservationForm.errors?.['invalidDates']) {
      this.errorMessage = 'La date de fin doit être postérieure à la date de début.';
      return;
    }

    if (this.reservationForm.errors?.['invalidDateFormat']) {
      this.errorMessage = 'Les dates saisies ne sont pas dans un format valide.';
      return;
    }

    const reservationData: Reservation = {
      id: this.editingReservation?.id ?? 0, // Assurez-vous que id est défini
      nom: this.reservationForm.value.nom,
      start_date: new Date(this.reservationForm.value.start_date).toISOString().split('T')[0], // Format "YYYY-MM-DD"
      end_date: new Date(this.reservationForm.value.end_date).toISOString().split('T')[0], // Format "YYYY-MM-DD"
      status: this.editingReservation?.status ?? 'PENDING' // Valeur par défaut si status est undefined
    };

    if (this.isEditing && this.editingReservation) {
      this.reservationService.updateReservation(reservationData).subscribe({
        next: () => {
          this.successMessage = 'Réservation mise à jour avec succès !';
          this.loadReservations();
          this.resetForm();
          setTimeout(() => {
            this.successMessage = null;
          }, 3000);
        },
        error: (error) => {
          this.errorMessage = 'Erreur lors de la mise à jour de la réservation : ' + (error.message || 'Une erreur est survenue');
          console.error('Erreur lors de la mise à jour:', error);
        }
      });
    }
  }

  editReservation(reservation: Reservation): void {
    this.isEditing = true;
    this.editingReservation = reservation;
    this.reservationForm.patchValue({
      nom: reservation.nom,
      start_date: reservation.start_date,
      end_date: reservation.end_date
    });
  }

  deleteReservation(reservation: Reservation): void {
    if (!reservation.id) {
      this.errorMessage = 'Erreur : ID de la réservation manquant.';
      return;
    }

    const confirmDelete = window.confirm(
      `Êtes-vous sûr de vouloir supprimer la réservation de "${reservation.nom}" (ID: ${reservation.id}) ?`
    );
    if (confirmDelete) {
      this.reservationService.deleteReservation(reservation.id).subscribe({
        next: () => {
          this.successMessage = `Réservation de "${reservation.nom}" (ID: ${reservation.id}) supprimée avec succès !`;
          this.loadReservations();
          setTimeout(() => {
            this.successMessage = null;
          }, 3000);
        },
        error: (error) => {
          this.errorMessage = 'Erreur lors de la suppression de la réservation : ' + (error.message || 'Une erreur est survenue');
          console.error('Erreur lors de la suppression:', error);
        }
      });
    }
  }

  resetForm(): void {
    this.reservationForm.reset();
    this.isEditing = false;
    this.editingReservation = null;
    this.errorMessage = null;
  }
}