package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.api.exceptions.UsernameExistsException
import com.ivanfilip.airsorsix.configuration.PasswordEncoderConfiguration
import com.ivanfilip.airsorsix.configuration.SecurityConfiguration
import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.domain.User
import com.ivanfilip.airsorsix.service.FlightService
import com.ivanfilip.airsorsix.service.LocationService
import com.ivanfilip.airsorsix.service.ReservationService
import com.ivanfilip.airsorsix.service.UserService
import org.springframework.http.HttpRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse
import javax.validation.Valid
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.context.SecurityContextPersistenceFilter
import kotlin.math.log
import org.springframework.web.bind.annotation.RequestMapping




@RestController
@RequestMapping("/api")
class ApiPublicController(val flightService: FlightService,
                          val userService: UserService,
                          val locationService: LocationService,
                          val reservationService: ReservationService) {


    @GetMapping("/origin/")
    fun departureLocations(@RequestParam(value = "origin", required = false) origin: String?): List<Location>? =
            flightService.getDepartureLocations(origin)


    @GetMapping("/location/")
    fun locationByAirport(@RequestParam(value = "airport") airport: String): Location? =
            locationService.getLocationByAirport(airport)


    @GetMapping("/destinations/")
    fun destinationLocations(@RequestParam(value = "origin", required = true) origin: String,
                             @RequestParam(value = "destination", required = false) destination: String?): List<Location>? =
            flightService.getArrivalLocations(origin, destination)


    @GetMapping("flights/{id}")
    fun flightById(@PathVariable(name = "id") id: String): Flight? = flightService.getFlightById(id)


    @GetMapping("flights/{origin}/{destination}")
    fun flightsByPath(@PathVariable(name = "origin") origin: String,
                      @PathVariable(name = "destination") destination: String): List<Flight>? =
            flightService.getFlightsByLocation(origin, destination)


    @GetMapping("reservations/{flightId}")
    fun reservationsByFlight(@PathVariable(name = "flightId") flightId: String): List<Reservation>? =
            reservationService.findReservationsByFlight(flightId)


    @PostMapping("/register")
    fun createUser(@RequestBody @Valid user: CreateUserRequest): ResponseEntity<String> {
        userService.addNewUser(user)
        return ResponseEntity("User created!", HttpStatus.CREATED)
    }


    @GetMapping("/user")
    fun user(principal: Principal): User = userService.getUserForPrincipal(principal)


    @GetMapping("/principal")
    fun principal(principal: Principal): Principal {
        return principal
    }

    @GetMapping("/login")
    fun login(): Boolean {
        return true
    }


    @GetMapping("/logout")
    fun logout(): Any {
        return SecurityContextHolder.clearContext()
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    fun onDuplicateUsernameError(e: UsernameExistsException) = mapOf("error" to e.message)

}
