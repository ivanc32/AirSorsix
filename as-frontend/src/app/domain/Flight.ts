import {Plane} from './Plane';
import {Location} from './Location';

export interface Flight {
  id: string;
  plane: Plane;
  code: string;
  departureDateTime: string;
  arrivalDateTime: string;
  departureLocation: Location;
  arrivalLocation: Location;
  businessSeats: number;
  economySeats: number;
}
