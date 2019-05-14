package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.LocationRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class FlightService(val flightRepository: FlightRepository,
                    val locationRepository: LocationRepository) {

    fun getDepartureLocations(): List<Location>?{
        return flightRepository.findAllDistinctDepartureLocations()
    }

    fun getArrivalLocations(origin: String): List<Location>?{
        return flightRepository.findAllDistinctArrivalLocations(locationRepository.findLocationByAirport(origin))
    }

    fun getFlightsByLocation(origin: String, destination: String): List<Flight>?{
        return flightRepository
                .findAllByDepartureLocationAndArrivalLocation(locationRepository.findLocationByAirport(origin), locationRepository.findLocationByAirport(destination))
    }
}
