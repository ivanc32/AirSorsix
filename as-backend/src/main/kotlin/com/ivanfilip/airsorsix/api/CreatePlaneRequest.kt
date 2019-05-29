package com.ivanfilip.airsorsix.api

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class CreatePlaneRequest(
        @NotNull
        @Min(1)
        val priceBusinessSeat: Int,

        @NotNull
        @Min(1)
        val numberBusinessSeat: Int,

        @NotNull
        @Min(1)
        val priceEconomySeat: Int,

        @NotNull
        @Min(1)
        val numberEconomySeat: Int,

        @NotNull
        val manufacturer: String,

        @NotNull
        val model: String
)
