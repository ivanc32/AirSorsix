package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.service.FlightService
import com.ivanfilip.airsorsix.service.LocationService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController(val flightService: FlightService,
                    val locationService: LocationService) {

    @PostMapping("/create/location")
    fun createLocation(@RequestBody location: CreateLocationRequest): Location {
        return locationService.addNewLocation(location.city, location.country, location.airport, location.price)
    }

    /*@PostMapping("/create/plane")
    fun createPlane(@RequestBody plane: CreatePlaneRequest): Plane {
        return planeService.addNewLocation(plane.city, location.country, location.airport, location.price)
    }*/

    /*@PostMapping("/create/location")
    fun createPoll(@RequestBody location: CreateLocationRequest): Location {
        return locationService.addNewLocation(location.city, location.country, location.airport, location.price)
    }*/
}