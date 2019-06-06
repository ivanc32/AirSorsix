package com.ivanfilip.airsorsix.domain

import org.hibernate.annotations.GenericGenerator
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import java.time.LocalDateTime
import javax.persistence.*


@Entity
@Table(name = "flights")
data class Flight(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        val id: String = "",

        @ManyToOne
        @JoinColumn(name = "plane_id", nullable = false)
        val plane: Plane,

        @Column(name = "code", updatable = false, nullable = false)
        val code: String,

        @Column(name = "dt_of_departure", nullable = false)
        val departureDateTime: LocalDateTime,

        @Column(name = "dt_of_arrival", nullable = false)
        val arrivalDateTime: LocalDateTime,

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
