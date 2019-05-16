package com.ivanfilip.airsorsix.repository

import com.ivanfilip.airsorsix.domain.Reservation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ReservationRepository : JpaRepository<Reservation, String> {
}