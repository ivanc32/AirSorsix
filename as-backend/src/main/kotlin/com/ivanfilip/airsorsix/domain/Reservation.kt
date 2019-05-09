package com.ivanfilip.airsorsix.domain

import com.ivanfilip.airsorsix.custom_types.SeatType
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "reservations")
data class Reservation(
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        @Id
        val id: String,

        @ManyToOne
        @JoinColumn(name = "flight_id")
        val flight: Flight,

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,

        @Column(name = "seat_type", nullable = false)
        val seatType: SeatType,

        @Column(name = "date_reserved", nullable = false)
        val dateReserved: ZonedDateTime = ZonedDateTime.now()
)