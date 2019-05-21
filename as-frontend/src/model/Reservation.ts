import { Flight } from './Flight';

export interface Reservation {
  flightId: string;
  userId: string;
  economyTickets: number;
  businessTickets: number;
  }
