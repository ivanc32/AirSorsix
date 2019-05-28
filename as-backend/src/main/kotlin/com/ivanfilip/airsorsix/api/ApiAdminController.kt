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
class ApiAdminController(val flightService: FlightService,
                         val locationService: LocationService,
                         val planeService: PlaneService) {

    @PostMapping("/location")
    fun createLocation(@RequestBody @Valid location: CreateLocationRequest): Location =
            locationService.addNewLocation(location)


    @PostMapping("/plane")
    fun createPlane(@RequestBody @Valid plane: CreatePlaneRequest): Plane =
            planeService.addNewPlane(plane)

    @PostMapping("/flight")
    fun createFlight(@RequestBody @Valid flight: CreateFlightRequest): ResponseEntity<List<Flight>?> =
            flightService.addNewFlight(flight)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
}
