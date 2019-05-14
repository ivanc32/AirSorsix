package com.ivanfilip.airsorsix.service
import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import com.ivanfilip.airsorsix.repository.FlightRepository
import com.ivanfilip.airsorsix.repository.LocationRepository
import com.ivanfilip.airsorsix.repository.PlaneRepository
import com.ivanfilip.airsorsix.utills.generateId
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.*
import javax.transaction.Transactional

@Service
class FlightService(val flightRepository: FlightRepository,
                    val locationRepository: LocationRepository,
                    val planeRepository: PlaneRepository) {

    val logger = loggerFor<FlightService>()

    fun getDepartureLocations(): List<Location>?{
        return flightRepository.findAllDistinctDepartureLocations()
    }

    fun getArrivalLocations(origin: String): List<Location>?{
        return flightRepository.findAllDistinctArrivalLocations(locationRepository.findLocationByAirport(origin))
    }

    fun getFlightsByLocation(origin: String, destination: String): List<Flight>?{
        return flightRepository
                .findAllByDepartureLocationAndArrivalLocation(locationRepository.findLocationByAirport(origin),
                        locationRepository.findLocationByAirport(destination))
    }

    @Transactional
    fun addNewFlight(planeId: String, code: String, departureDateTime: LocalDateTime,
                     arrivalDateTime: LocalDateTime, departureLocationId: String,
                     arrivalLocationId: String, businessSeats: Int,
                     economySeats: Int): Optional<Flight> {
        val flight = Flight(generateId(), planeRepository.findPlaneById(planeId) ?: return Optional.empty(),
                code, departureDateTime, arrivalDateTime,
                locationRepository.findLocationById(departureLocationId) ?: return Optional.empty(),
                locationRepository.findLocationById(arrivalLocationId) ?: return Optional.empty(),
                businessSeats, economySeats)
        logger.info("Saving flight [{}]", flight)
        flightRepository.save(flight)
        return Optional.of(flight)
    }
}
