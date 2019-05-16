package com.ivanfilip.airsorsix.domain

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "planes")
data class Plane(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        val id: String = "",

        @Column(name = "manufacturer")
        val manufacturer: String,

        @Column(name = "model")
        val model: String,

        @Column(name = "number_of_economy_seats", nullable = false)
        val economySeats: Int?,

        @Column(name = "number_of_business_seats", nullable = false)
        val businessSeats: Int?,

        @Column(name = "price_of_economy_seat", nullable = false)
        val economyPrice: Int,

        @Column(name = "price_of_business_seat", nullable = false)
        val businessPrice: Int
)