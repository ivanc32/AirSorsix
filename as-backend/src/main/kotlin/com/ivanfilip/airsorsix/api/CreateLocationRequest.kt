package com.ivanfilip.airsorsix.api

import javax.validation.constraints.Min
import javax.validation.constraints.NotNull

class CreateLocationRequest(
        @NotNull
        val city: String = "",

        @NotNull
        val country: String = "",

        @NotNull
        val airport: String = "",

        @NotNull
        @Min(1)
        val price: Int = 0
)
