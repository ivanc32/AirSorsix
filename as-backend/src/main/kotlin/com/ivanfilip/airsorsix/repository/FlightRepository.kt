package com.ivanfilip.airsorsix.repository

import com.ivanfilip.airsorsix.domain.Flight
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FlightRepository : JpaRepository<Flight, String>{

}