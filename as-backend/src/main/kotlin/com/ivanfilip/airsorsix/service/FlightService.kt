package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.LocationRepository
import com.ivanfilip.airsorsix.repository.PlaneRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import javax.transaction.Transactional
import java.time.format.DateTimeFormatter



@Service
class FlightService(val flightRepository: FlightRepository,
                    val locationRepository: LocationRepository,
                    val planeRepository: PlaneRepository) {

    val logger = loggerFor<FlightService>()

    fun getDepartureLocations(origin: String? = ""): List<Location>?{
        val locations: List<Location>? = flightRepository.findAllDistinctDepartureLocations()
        return locations?.filter { it.city.startsWith(origin ?: return locations, ignoreCase = true) }
    }


    fun getArrivalLocations(origin: String, destination: String?): List<Location>?{
        val locations: List<Location>? = flightRepository.findAllDistinctArrivalLocations(locationRepository.findLocationByAirport(origin))
        return locations?.filter { it.city.startsWith(destination ?: return locations, ignoreCase = true) }
    }

    fun getFlightsByLocation(origin: String, destination: String): List<Flight>?{
        return flightRepository
                .findAllByDepartureLocationAndArrivalLocation(locationRepository.findLocationByAirport(origin),
                        locationRepository.findLocationByAirport(destination))
    }

    @Transactional
    fun addNewFlight(planeId: String, code: String, departureDateTime: String,
                     arrivalDateTime: String, departureLocationId: String,
                     arrivalLocationId: String): Flight? {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")
        val flight = Flight(plane = planeRepository.findPlaneById(planeId) ?: return null,
                code = code, departureDateTime = LocalDateTime.parse(departureDateTime, formatter),
                arrivalDateTime = LocalDateTime.parse(arrivalDateTime, formatter),
                departureLocation = locationRepository.findLocationById(departureLocationId) ?: return null,
                arrivalLocation = locationRepository.findLocationById(arrivalLocationId) ?: return null)
        logger.info("Saving flight [{}]", flight)
        flightRepository.save(flight)
        return flight
    }
}
