package com.ivanfilip.airsorsix.api

import org.springframework.format.annotation.DateTimeFormat
import java.time.LocalDateTime
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class CreateFlightRequest (
        @NotNull
        val planeId: String,

        @NotNull
        val code: String,

        @NotNull
        @DateTimeFormat
        val departureDateTime: LocalDateTime,

        @NotNull
        @DateTimeFormat
        val arrivalDateTime: LocalDateTime,

        @NotNull
        val departureLocationId: String,

        @NotNull
        val arrivalLocationId: String,

        @NotNull
        @Min(1)
        val businessSeats: Int,

        @NotNull
        @Min(1)
        val economySeats: Int
)
