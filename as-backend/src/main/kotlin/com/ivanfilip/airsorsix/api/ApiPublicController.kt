package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.domain.User
import com.ivanfilip.airsorsix.service.FlightService
import com.ivanfilip.airsorsix.service.LocationService
import com.ivanfilip.airsorsix.service.ReservationService
import com.ivanfilip.airsorsix.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
class ApiPublicController(val flightService: FlightService,
                          val userService: UserService,
                          val locationService: LocationService,
                          val reservationService: ReservationService){


    @PostMapping("/flight")
    fun createFlight(@RequestBody @Valid flight: CreateFlightRequest): ResponseEntity<List<Flight>> {
        return flightService.addNewFlight(flight.planeId, flight.code, flight.departureDateTime,
                flight.arrivalDateTime, flight.departureLocationId, flight.arrivalLocationId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }

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

    @GetMapping("flights/{id}")
    fun flightById(@PathVariable(name = "id") id: String): Flight? {
        return flightService.getFlightById(id)
    }

    @GetMapping("flights/{origin}/{destination}")
    fun flightsByPath(@PathVariable(name = "origin") origin: String,
                      @PathVariable(name = "destination") destination: String): List<Flight>?{
        return flightService.getFlightsByLocation(origin, destination)
    }

    @GetMapping("reservations/{flightId}")
    fun reservationsByFlight(@PathVariable(name = "flightId") flightId: String): List<Reservation>? {
        return reservationService.findReservationsByFlight(flightId)
    }

    @PostMapping("/register")
    fun createUser(@RequestBody @Valid user: CreateUserRequest): ResponseEntity<String> {
        userService.addNewUser(user)
        return ResponseEntity("User created!", HttpStatus.CREATED)
    }

    @GetMapping("/user")
    fun user(principal: Principal): User {
        return userService.getUserForPrincipal(principal)
    }

    /*@GetMapping("/login")
    fun logIn(principal: Principal, response: HttpServletResponse, request: HttpServletRequest): Principal = principal
*/}
