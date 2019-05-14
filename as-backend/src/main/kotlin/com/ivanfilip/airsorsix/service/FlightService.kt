package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.utills.generateId
import com.ivanfilip.airsorsix.utills.loggerFor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.logging.Logger
import javax.transaction.Transactional

@Service
class FlightService(val repository: FlightRepository) {
    val logger = loggerFor<FlightService>()

    @Transactional
    fun addNewFlight(planeId: String, code: String, departureDateTime: LocalDateTime,
                     arrivalDateTime: LocalDateTime, departureLocationId: String,
                     arrivalLocationId: String, businessSeats: Int,
                     economySeats: Int): Flight {
        val flight = Flight(generateId(), planeId, code, )
        logger.info("Saving flight [{}]", flight)
        repository.save(flight)
        return flight
    }
}
