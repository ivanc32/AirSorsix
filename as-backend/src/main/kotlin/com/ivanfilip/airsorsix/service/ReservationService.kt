package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.ReservationRepository
import com.ivanfilip.airsorsix.repository.UserRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service

@Service
class ReservationService(val reservationRepository: ReservationRepository,
                         val userRepository: UserRepository,
                         val flightRepository: FlightRepository) {

    val logger = loggerFor<ReservationService>()

    fun addNewReservation(flightId: String, userId: String,
                          economyTickets: Int, businessTicket: Int): Reservation? {
        val reservation = Reservation(user = userRepository.findUserById(userId) ?: return null,
                flight = flightRepository.findFlightById(flightId) ?: return null,
                economyTickets = economyTickets,
                businessTickets = businessTicket)
        reservationRepository.save(reservation)
        logger.info("Add reservation with details [{}]", reservation)
        return reservation
    }

    fun findReservationsByFlight(flightId: String): List<Reservation>? {
        logger.info("find reservations for flight with id [{}]", flightId)
        return reservationRepository.findReservationsByFlight(flightRepository.findFlightById(flightId))
    }
}
