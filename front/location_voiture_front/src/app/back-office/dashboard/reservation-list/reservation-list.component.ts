import { Component, OnInit } from '@angular/core';
import { ReservationService } from '../../services/reservation.service';
import { Reservation } from '../../models/reservation.model';

@Component({
  selector: 'app-reservation-list',
  templateUrl: './reservation-list.component.html',
  styleUrls: ['./reservation-list.component.css'],
})
export class ReservationListComponent implements OnInit {
  reservations: Reservation[] = [];
  filteredReservations: Reservation[] = [];
  paginatedReservations: Reservation[] = [];
  successMessage: string | null = null;
  errorMessage: string | null = null;
  statusFilter: string = '';
  searchQuery: string = '';
  currentPage: number = 1;
  itemsPerPage: number = 3; // Changé à 3 pour afficher 3 lignes par page
  totalPages: number = 1;

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.loadReservations();
  }

  loadReservations(): void {
    this.reservationService.getAllReservations().subscribe(
      (reservations: Reservation[]) => {
        this.reservations = reservations;
        this.currentPage = 1; // Réinitialiser à la première page
        this.filterReservations();
      },
      (error) => {
        this.errorMessage = 'Erreur lors du chargement des réservations : ' + error.message;
        console.error('Erreur lors du chargement des réservations:', error);
      }
    );
  }

  filterReservations(): void {
    let filtered = [...this.reservations];

    // Filtrer par statut
    if (this.statusFilter) {
      filtered = filtered.filter(reservation => reservation.status === this.statusFilter);
    }

    // Filtrer par recherche (nom)
    if (this.searchQuery) {
      filtered = filtered.filter(reservation =>
        reservation.nom.toLowerCase().includes(this.searchQuery.toLowerCase())
      );
    }

    this.filteredReservations = filtered;
    this.currentPage = 1; // Réinitialiser à la première page lors d'un filtrage
    this.updatePagination();
  }

  updatePagination(): void {
    // Calculer le nombre total de pages
    this.totalPages = Math.ceil(this.filteredReservations.length / this.itemsPerPage);

    // S'assurer que la page actuelle est valide
    if (this.currentPage > this.totalPages) {
      this.currentPage = this.totalPages || 1;
    }

    // Calculer les réservations paginées
    const startIndex = (this.currentPage - 1) * this.itemsPerPage;
    const endIndex = startIndex + this.itemsPerPage;
    this.paginatedReservations = this.filteredReservations.slice(startIndex, endIndex);
  }

  previousPage(): void {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updatePagination();
    }
  }

  nextPage(): void {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updatePagination();
    }
  }

  onStatusChange(event: Event, reservation: Reservation): void {
    const target = event.target as HTMLSelectElement;
    if (target) {
      const newStatus = target.value;
      if (newStatus) {
        const confirmChange = window.confirm(
          `Êtes-vous sûr de vouloir changer le statut de la réservation de "${reservation.nom}" (ID: ${reservation.id}) à "${newStatus === 'CONFIRMED' ? 'Confirmé' : newStatus === 'PENDING' ? 'En attente' : 'Annulé'}" ?`
        );
        if (confirmChange) {
          this.updateStatus(reservation, newStatus);
        } else {
          target.value = reservation.status || ''; // Réinitialiser à la valeur actuelle ou vide
        }
      }
    }
  }

  updateStatus(reservation: Reservation, newStatus: string): void {
    const updatedReservation = { ...reservation, status: newStatus };
    this.reservationService.updateReservation(updatedReservation).subscribe(
      () => {
        this.successMessage = `Statut de la réservation de "${reservation.nom}" (ID: ${reservation.id}) mis à jour avec succès !`;
        reservation.status = newStatus;
        this.filterReservations();
        setTimeout(() => {
          this.successMessage = null;
        }, 3000);
      },
      (error) => {
        this.errorMessage = 'Erreur lors de la mise à jour du statut : ' + error.message;
        console.error('Erreur lors de la mise à jour du statut:', error);
      }
    );
  }
}