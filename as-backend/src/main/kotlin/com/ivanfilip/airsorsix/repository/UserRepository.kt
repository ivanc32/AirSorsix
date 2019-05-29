package com.ivanfilip.airsorsix.repository

import com.ivanfilip.airsorsix.domain.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, String>{
    fun existsUserByUsernameAndProviderEquals (username :String, provider: String): Boolean

    fun findUserByUsername (username: String): User?

    fun findUserById(id: String): User?

    fun existsUserByUsername (username: String): Boolean

}
