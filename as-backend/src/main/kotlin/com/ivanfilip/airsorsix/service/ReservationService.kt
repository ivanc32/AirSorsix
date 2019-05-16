package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.custom_types.SeatType
import com.ivanfilip.airsorsix.domain.Reservation
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.ReservationRepository
import com.ivanfilip.airsorsix.repository.UserRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service

@Service
class ReservationService (val reservationRepository: ReservationRepository,
                          val userRepository: UserRepository,
                          val flightRepository: FlightRepository) {

    val logger = loggerFor<ReservationService>()

    fun addNewReservation(userId: String, flightId: String, seatType: SeatType): Reservation? {
        val reservation = Reservation(flight = flightRepository.findFlightById(flightId) ?: return null,
                user = userRepository.findUserById(userId) ?: return null,seatType =  seatType)
        logger.info("Saving reservation [{}]", reservation)
        reservationRepository.save(reservation)
        return reservation
    }
}
