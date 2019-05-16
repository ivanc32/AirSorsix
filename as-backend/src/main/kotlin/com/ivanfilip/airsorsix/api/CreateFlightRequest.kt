package com.ivanfilip.airsorsix.api

import org.springframework.format.annotation.DateTimeFormat
import javax.validation.constraints.NotNull

class CreateFlightRequest (
        @NotNull
        val planeId: String,

        @NotNull
        val code: String,

        @NotNull
        @DateTimeFormat
        val departureDateTime: String,

        @NotNull
        @DateTimeFormat
        val arrivalDateTime: String,

        @NotNull
        val departureLocationId: String,

        @NotNull
        val arrivalLocationId: String
)
