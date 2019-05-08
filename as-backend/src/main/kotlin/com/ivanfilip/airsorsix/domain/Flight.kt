package com.ivanfilip.airsorsix.domain;

import java.time.LocalDateTime
import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "flights")
data class Flight(
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        @Id
        val id: String,

        @Column(name = "code", updatable = false, nullable = false)
        val code: String,

        @Column(name = "dt_of_departure", nullable = false)
        val departureDateTime: ZonedDateTime,

        @Column(name = "dt_of_arrival", nullable = false)
        val arrivalDateTime: ZonedDateTime,

        @OneToOne
        @Column(name = "departure_location", nullable = false)
        val departureLocation: Location,

        @OneToOne
        @Column(name = "arrival_location", nullable = false)
        val arrivalLocation: Location
        )
