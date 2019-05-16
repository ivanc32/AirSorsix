package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Plane
import com.ivanfilip.airsorsix.repository.PlaneRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaneService(val repository: PlaneRepository) {

    val logger = loggerFor<PlaneService>()

    @Transactional
    fun addNewPlane(manufacturer: String, model: String, numberBusinessSeat: Int, priceBusinessSeat: Int,
                    numberEconomySeat: Int, priceEconomySeat: Int): Plane {
        val plane = Plane(manufacturer =  manufacturer, model = model,
                economySeats =  numberEconomySeat, businessSeats = numberBusinessSeat,
                economyPrice = priceEconomySeat, businessPrice = priceBusinessSeat)
        logger.info("Saving plane [{}]", plane)
        repository.save(plane)
        return plane
    }
}
