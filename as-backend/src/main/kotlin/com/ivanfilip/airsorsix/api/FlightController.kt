package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.service.FlightService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/home")
class FlightController(val flightService: FlightService){


    @GetMapping("/origins")
    fun departureLocations(): List<Location>?{
        return flightService.getDepartureLocations()
    }

    @GetMapping("destinations/{origin}")
    fun destinationLocations(@PathVariable(name = "origin")origin: String): List<Location>?{
        return flightService.getArrivalLocations(origin)
    }

    @GetMapping("flights/{origin}/{destination}")
    fun flightsByPath(@PathVariable(name = "origin") origin: String, @PathVariable(name = "destination") destination: String): List<Flight>?{
        return flightService.getFlightsByLocation(origin, destination)
    }

}