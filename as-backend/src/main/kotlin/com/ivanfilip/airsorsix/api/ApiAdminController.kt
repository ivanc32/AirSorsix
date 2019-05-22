package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.domain.Plane
import com.ivanfilip.airsorsix.service.FlightService
import com.ivanfilip.airsorsix.service.LocationService
import com.ivanfilip.airsorsix.service.PlaneService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/create")
@CrossOrigin("*")
class ApiAdminController(val flightService: FlightService,
                         val locationService: LocationService,
                         val planeService: PlaneService) {

    @PostMapping("/location")
    fun createLocation(@RequestBody @Valid location: CreateLocationRequest): Location {
        return locationService.addNewLocation(location.city, location.country, location.airport,
                location.price)
    }

    @PostMapping("/plane")
    fun createPlane(@RequestBody @Valid plane: CreatePlaneRequest): Plane {
        return planeService.addNewPlane(plane.manuufacturer, plane.model, plane.numberOfEconomySeat,
                plane.numberOfBusinessSeat, plane.priceOfEconomySeat, plane.priceOfBusinessSeat)
    }

    @PostMapping("/flight")
    fun createFlight(@RequestBody @Valid flight: CreateFlightRequest): ResponseEntity<List<Flight>?> {
        return flightService.addNewFlight(flight.planeId, flight.code, flight.departureDateTime,
                flight.arrivalDateTime, flight.departureLocationId, flight.arrivalLocationId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }
}
