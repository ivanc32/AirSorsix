package com.ivanfilip.airsorsix.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table


@Entity
@Table(name = "planes")
data class Plane(
        @Column(name = "id", unique = true, updatable = false, nullable = false)
        @Id
        val id: String,

        @Column(name = "model")
        val model: String,

        @Column(name = "manufacturer")
        val manufacturer: String,

        @Column(name = "rows_of_seats", nullable = false)
        val seatsRows: Int,

        @Column(name = "columns_of_seats", nullable = false)
        val seatsColumns: Int,

        @Column(name = "price", nullable = false)
        val price: Int,

        @Column(name = "first_class_rows") //how many of the starting rows are first class
        val firstClassRows: Int
)