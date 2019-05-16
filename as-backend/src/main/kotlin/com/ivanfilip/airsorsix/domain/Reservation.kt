package com.ivanfilip.airsorsix.domain

import com.ivanfilip.airsorsix.custom_types.SeatType
import org.hibernate.annotations.GenericGenerator
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "reservations")
data class Reservation(
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        val id: String = "",

        @ManyToOne
        @JoinColumn(name = "flight_id")
        val flight: Flight,

        @ManyToOne
        @JoinColumn(name = "user_id")
        val user: User,

        @Column(name = "seat_type", nullable = false)
        val seatType: SeatType,

        @Column(name = "date_reserved", nullable = false)
        val dateReserved: LocalDateTime = LocalDateTime.now()
)