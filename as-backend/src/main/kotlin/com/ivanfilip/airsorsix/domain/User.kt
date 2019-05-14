package com.ivanfilip.airsorsix.domain

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "users")
data class User (
        @Id
        @Column(name = "id", unique = true, nullable = false)
        val id: String,

        @Column(name = "username", unique = true, nullable = false)
        val username: String,

        @Column(name = "password", unique = true, nullable = false)
        val password: String,

        @Column(name = "roles", unique = true, nullable = false)
        val roles: String
)
