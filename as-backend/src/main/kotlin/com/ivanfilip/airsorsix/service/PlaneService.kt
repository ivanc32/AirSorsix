package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.api.CreatePlaneRequest
import com.ivanfilip.airsorsix.domain.Plane
import com.ivanfilip.airsorsix.repository.PlaneRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PlaneService(val planeRepository: PlaneRepository) {

    val logger = loggerFor<PlaneService>()

    @Transactional
    fun addNewPlane(planeDto: CreatePlaneRequest): Plane {
        val plane = Plane(manufacturer =  planeDto.manufacturer,
                model = planeDto.model,
                economySeats =  planeDto.numberEconomySeat,
                businessSeats = planeDto.numberBusinessSeat,
                economyPrice = planeDto.priceEconomySeat,
                businessPrice = planeDto.priceBusinessSeat)
        logger.info("Saving plane [{}]", plane)
        planeRepository.save(plane)
        return plane
    }
}
