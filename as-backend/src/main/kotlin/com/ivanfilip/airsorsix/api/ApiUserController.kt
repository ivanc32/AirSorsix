package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.service.ReservationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.validation.Valid

@RestController
@RequestMapping("/api/user/create")
@CrossOrigin("*")
class ApiUserController(val reservationService: ReservationService) {

    @PostMapping("/reservation")
    fun addNewReservation(@RequestBody @Valid reservation: CreateReservationRequest): ResponseEntity<Reservation?> =
            reservationService.addNewReservation(
                reservation.flightId, reservation.userId,
                reservation.economyTickets, reservation.businessTickets)
                ?.let {
                    ResponseEntity.ok(it)
                } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
}
