package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.api.CreateUserRequest
import com.ivanfilip.airsorsix.api.exceptions.UsernameExistsException
import com.ivanfilip.airsorsix.configuration.PasswordEncoderConfiguration
import com.ivanfilip.airsorsix.domain.User
import com.ivanfilip.airsorsix.repository.UserRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.context.event.EventListener
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.authentication.event.AuthenticationSuccessEvent
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.security.oauth2.client.authentication.OAuth2LoginAuthenticationToken
import java.lang.Exception
import java.security.Principal

@Service
class UserService(val userRepository: UserRepository,
                  val encoder: PasswordEncoderConfiguration) : UserDetailsService {

    val logger = loggerFor<UserService>()

    override fun loadUserByUsername(username: String): User? =
            userRepository.findUserByUsername(username)


    fun existsUserByUsername(username: String, provider: String): Boolean =
            if (provider == "") userRepository.existsUserByUsername(username)
            else userRepository.existsUserByUsernameAndProviderEquals(username, provider)

    fun addNewUser(userDto: CreateUserRequest): User? =
            if (existsUserByUsername(userDto.username, userDto.provider)) throw UsernameExistsException()
            else userRepository.save(User(username = userDto.username,
                    password = encoder.passwordEncoder().encode(userDto.password),
                    provider = userDto.provider,
                    role = userDto.role))

    /*fun findUserById(id:String): User =
            userRepository.findByIdOrNull(id) ?: throw Exception("there is no user with such Id")

   @EventListener
    fun onAuthenticationSuccess(authenticationSuccessEvent: AuthenticationSuccessEvent) {
        val authentication = authenticationSuccessEvent.authentication as OAuth2LoginAuthenticationToken
        logger.info("Authentication success [{}]", authenticationSuccessEvent)
        val id = authentication.name
        val provider = authentication.clientRegistration.registrationId
        val (user, newUser) = userRepository.findById(id)
                .map { Pair(it, false) }
                .orElseGet {
                    Pair(User(), true)
                }
        if (newUser) {
            logger.info("Creating new user [{}]", user)
            userRepository.save(user)
        }
    }*/

    fun getUserForPrincipal(principal: Principal): User {
        return userRepository.findById(principal.name).orElseThrow {
            Exception("User with id [${principal.name}] not found")
        }
    }


}

