package com.ivanfilip.airsorsix.repository

import com.ivanfilip.airsorsix.domain.Flight
import com.ivanfilip.airsorsix.domain.Location
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{
    @Query("SELECT DISTINCT f.departureLocation FROM Flight f")
    fun findAllDistinctDepartureLocations(): List<Location>?

    @Query("SELECT DISTINCT f.arrivalLocation FROM Flight f WHERE f.departureLocation = :origin")
    fun findAllDistinctArrivalLocations(@Param("origin") origin: Location?): List<Location>?

    fun findAllByDepartureLocationAndArrivalLocation(origin: Location?, destination: Location?): List<Flight>?

    fun findFlightById(id: String): Flight?
}