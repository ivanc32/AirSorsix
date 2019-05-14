package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.LocationRepository
import com.ivanfilip.airsorsix.utills.generateId
import com.ivanfilip.airsorsix.utills.loggerFor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import java.util.logging.Logger

@Service
class LocationService(val repository: LocationRepository) {

    val logger = loggerFor<LocationService>()

    @Transactional
    fun addNewLocation(city: String, country: String, airport: String, price: Int): Location {
        val location = Location(generateId(), city, country, airport, price)
        logger.info("Saving location [{}]", location)
        repository.save(location)
        return location
    }
}
