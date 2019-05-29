package com.ivanfilip.airsorsix.api

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

class LoginRequest (
        @NotNull
        val username: String,

        @Length(min = 7, max = 30)
        val password: String
)
