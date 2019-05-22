package com.ivanfilip.airsorsix.api

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class CreatePlaneRequest(
        @NotNull
        @Min(1)
        val priceOfBusinessSeat: Int,

        @NotNull
        @Min(1)
        val numberOfBusinessSeat: Int,

        @NotNull
        @Min(1)
        val priceOfEconomySeat: Int,

        @NotNull
        @Min(1)
        val numberOfEconomySeat: Int,

        @NotNull
        val manuufacturer: String,

        @NotNull
        val model: String
)
