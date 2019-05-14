package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.domain.Plane
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.LocationRepository
import com.ivanfilip.airsorsix.repository.PlaneRepository
import com.ivanfilip.airsorsix.utills.generateId
import com.ivanfilip.airsorsix.utills.loggerFor
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.PostMapping
import java.util.logging.Logger

@Service
class PlaneService(val repository: PlaneRepository) {

    val logger = loggerFor<PlaneService>()

    @Transactional
    fun addNewPlane(manufacturer: String, model: String, numberBusinessSeat: Int, priceBusinessSeat: Int,
                    numberEconomySeat: Int, priceEconomySeat: Int): Plane {
        val plane = Plane(generateId(), manufacturer, model, numberEconomySeat,
                numberBusinessSeat, priceEconomySeat, priceBusinessSeat)
        logger.info("Saving plane [{}]", plane)
        repository.save(plane)
        return plane
    }
}
