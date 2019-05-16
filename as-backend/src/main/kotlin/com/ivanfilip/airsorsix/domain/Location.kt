package com.ivanfilip.airsorsix.domain

import org.hibernate.annotations.GenericGenerator
import javax.persistence.*

@Entity
@Table(name = "locations")
data class Location(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        val id: String = "",

        @Column(name = "city", nullable = false)
        val city: String,

        @Column(name = "country", nullable = false)
        val country: String,

        @Column(name = "airport", nullable = false)
        val airport: String,

        @Column(name = "price", nullable = false)
        val price: Int
)