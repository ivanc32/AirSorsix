package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.api.CreateLocationRequest
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.repository.LocationRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class LocationService(val locationRepository: LocationRepository) {

    val logger = loggerFor<LocationService>()

    fun getLocationByAirport(airport: String): Location? {
        logger.info("searching for location with airport [{}]", airport);
        return locationRepository.findLocationByAirport(airport)
    }

    @Transactional
    fun addNewLocation(locationDto: CreateLocationRequest): Location {
        val location = Location(city = locationDto.city,
                country = locationDto.country,
                airport = locationDto.airport,
                price = locationDto.price)
        logger.info("Saving location [{}]", location)
        locationRepository.save(location)
        return location
    }
}
