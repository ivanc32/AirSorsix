package com.ivanfilip.airsorsix.domain

import org.apache.catalina.User
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "seats")
data class Seat(
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        @Id
        val id: String,

        @Column(name = "seat_row", nullable = false)
        val seatRow: Int,

        @Column(name = "seat_column", nullable = false)
        val seatColumn: Int,

        @Column(name = "date_reserved", nullable = false)
        val dateReserved: ZonedDateTime,

        @JoinColumn(name = "user_id")
        @ManyToOne
        val user: User
)