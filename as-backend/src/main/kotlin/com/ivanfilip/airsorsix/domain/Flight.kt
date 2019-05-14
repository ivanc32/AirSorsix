package com.ivanfilip.airsorsix.domain;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*

@EnableAutoConfiguration
@Entity
@Table(name = "flights")
data class Flight(
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        @Id
        val id: String,

        @ManyToOne
        @JoinColumn(name = "plane_id", nullable = false)
        val plane: Plane,

        @Column(name = "code", updatable = false, nullable = false)
        val code: String,

        @Column(name = "dt_of_departure", nullable = false)
        val departureDateTime: ZonedDateTime,

        @Column(name = "dt_of_arrival", nullable = false)
        val arrivalDateTime: ZonedDateTime,

        @ManyToOne
        @JoinColumn(name = "departure_location_id", nullable = false)
        val departureLocation: Location,

        @ManyToOne
        @JoinColumn(name = "arrival_location_id", nullable = false)
        val arrivalLocation: Location,

        @Column(name = "free_business_seats")
        val businessSeats: Int? = plane.businessSeats,

        @Column(name = "free_economy_seats")
        val economySeats: Int? = plane.economySeats
        )
