package com.ivanfilip.airsorsix.api

import javax.validation.constraints.NotNull

class CreateReservationRequest (
        @NotNull
        val flightId: String,

        @NotNull
        val userId: String,

        @NotNull
        val economyTickets: Int,

        @NotNull
        val businessTickets: Int
)
