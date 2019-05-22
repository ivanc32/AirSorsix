package com.ivanfilip.airsorsix.api

import org.hibernate.validator.constraints.Length
import javax.validation.constraints.NotNull

class CreateUserRequest (
        @NotNull
        val username: String,

        @NotNull
        @Length(min = 7, max = 30)
        val password: String,

        val role: String = "USER"
)
