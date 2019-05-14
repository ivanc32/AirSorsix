package com.ivanfilip.airsorsix.api

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class CreatePlaneRequest(
        @NotNull
        @Min(1)
        val priceOfBusinessSeat: Int = 0,

        @NotNull
        @Min(1)
        val numberOfBusinessSeat: Int = 0,

        @NotNull
        @Min(1)
        val priceOfEconomySeat: Int = 0,

        @NotNull
        @Min(1)
        val numberOfEconomySeat: Int = 0,

        @NotNull
        val manuufacturer: String = "",

        @NotNull
        val model: String = ""
)
