package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.domain.User
import com.ivanfilip.airsorsix.service.ReservationService
import com.ivanfilip.airsorsix.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.security.Principal
import javax.validation.Valid

@RestController
@RequestMapping("/api/user/")
@CrossOrigin("*")
class ApiUserController(val reservationService: ReservationService,
                        val userService: UserService) {

    @PostMapping("/reservation")
    fun addNewReservation(@RequestBody @Valid reservation: CreateReservationRequest): ResponseEntity<Reservation?> =
            reservationService.addNewReservation(
                reservation.flightId, reservation.userId,
                reservation.economyTickets, reservation.businessTickets)
                ?.let {
                    ResponseEntity.ok(it)
                } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build()

    @GetMapping("/logged")
    fun isLoggedIn() = true
}
