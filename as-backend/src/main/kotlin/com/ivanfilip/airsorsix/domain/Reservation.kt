package com.ivanfilip.airsorsix.domain

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
        @JoinColumn(name = "flight_id", nullable = false)
        val flight: Flight,

        @ManyToOne
        @JoinColumn(name = "user_id", nullable = false)
        val user: User,

        @Column(name = "number_of_economy_tickets", nullable = false)
        val economyTickets: Int = 0,

        @Column(name = "number_of_business_tickets", nullable = false)
        val businessTickets: Int = 0,

        @Column(name = "date_reserved", nullable = false)
        val dateReserved: LocalDateTime = LocalDateTime.now()
)
