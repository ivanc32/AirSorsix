package com.ivanfilip.airsorsix.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "locations")
data class Location(
        @Id
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        val id: String,

        @Column(name = "city", nullable = false)
        val city: String,

        @Column(name = "country", nullable = false)
        val country: String,

        @Column(name = "airport", nullable = false)
        val airport: String,

        @Column(name = "price", nullable = false)
        val price: Int
)