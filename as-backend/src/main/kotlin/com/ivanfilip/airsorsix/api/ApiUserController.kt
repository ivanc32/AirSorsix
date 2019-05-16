package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.service.ReservationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/api/user/create")
class ApiUserController(val reservationService: ReservationService) {

    @PostMapping("/reservation")
    fun addNewReservation(@RequestBody @Valid reservation: CreateReservationRequest): ResponseEntity<Reservation?> {
        return reservationService.addNewReservation(reservation.flightId,
                reservation.userId, reservation.seatType)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity.status(HttpStatus.BAD_REQUEST).build()
    }
}