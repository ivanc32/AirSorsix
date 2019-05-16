package com.ivanfilip.airsorsix.api

import com.ivanfilip.airsorsix.custom_types.SeatType
import javax.validation.constraints.NotNull

class CreateReservationRequest (
        @NotNull
        val flightId: String,

        @NotNull
        val userId: String,

        @NotNull
        val seatType: SeatType
)
