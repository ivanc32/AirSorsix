package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.service.FlightService
import com.ivanfilip.airsorsix.service.LocationService
import com.ivanfilip.airsorsix.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid

@CrossOrigin(origins = ["http://localhost:4200"])
@RestController
@RequestMapping("/api")
class ApiPublicController(val flightService: FlightService,
                          val userService: UserService,
                          val locationService: LocationService) {

    @GetMapping("/origin/")
    fun departureLocations(@RequestParam(value = "origin", required = false) origin: String?): List<Location>? {
        return flightService.getDepartureLocations(origin)
    }

    @GetMapping("/location/")
    fun locationByAirport(@RequestParam(value = "airport") airport: String): Location? {
        return locationService.getLocationByAirport(airport)
    }

    @GetMapping("/destinations/")
    fun destinationLocations(@RequestParam(value = "origin", required = true) origin: String,
                             @RequestParam(value = "destination", required = false) destination: String?): List<Location>? {
        return flightService.getArrivalLocations(origin, destination)
    }

    @GetMapping("flights/{origin}/{destination}")
    fun flightsByPath(@PathVariable(name = "origin") origin: String,
                      @PathVariable(name = "destination") destination: String): List<Flight>? {
        return flightService.getFlightsByLocation(origin, destination)
    }

    @PostMapping("/register")
    fun createUser(@RequestBody @Valid user: CreateUserRequest): ResponseEntity<String> {
        userService.addNewUser(user)
        return ResponseEntity("User created!", HttpStatus.CREATED)
    }

    @GetMapping("/login")
    fun logIn(principal: Principal, response: HttpServletResponse, request: HttpServletRequest): Principal = principal
}
