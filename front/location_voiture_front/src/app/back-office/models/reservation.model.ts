export interface Reservation {
    id?: number;
    nom: string;
    start_date: string; // Utilisation de string pour les dates dans le frontend (format ISO, ex. "2025-04-14")
    end_date: string;
    status: string;
  }