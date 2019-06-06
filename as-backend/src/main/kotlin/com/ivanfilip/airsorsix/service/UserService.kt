package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.api.CreateUserRequest
import com.ivanfilip.airsorsix.api.exceptions.UsernameExistsException
import com.ivanfilip.airsorsix.configuration.PasswordEncoderConfiguration
import com.ivanfilip.airsorsix.domain.User
import com.ivanfilip.airsorsix.repository.UserRepository
import com.ivanfilip.airsorsix.utills.loggerFor
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.Exception
import java.security.Principal

@Service
class UserService(val userRepository: UserRepository,
                  val encoder: PasswordEncoderConfiguration) : UserDetailsService {

    val logger = loggerFor<UserService>()

    override fun loadUserByUsername(username: String): User? {
        logger.info("load user by username: [{}]", username)
        return userRepository.findUserByUsername(username)
    }


    fun existsUserByUsername(username: String, provider: String): Boolean {
        logger.info("search for a user with username: [{}]", username)
        return if (provider == "") userRepository.existsUserByUsername(username)
        else userRepository.existsUserByUsernameAndProviderEquals(username, provider)
    }

    fun addNewUser(userDto: CreateUserRequest): User? {
        logger.info("save user if it does not exist [{}]", userDto)
        return if (existsUserByUsername(userDto.username, userDto.provider)) throw UsernameExistsException()
        else userRepository.save(User(username = userDto.username,
                password = encoder.passwordEncoder().encode(userDto.password),
                provider = userDto.provider,
                role = userDto.role))
    }


    fun getUserForPrincipal(principal: Principal): User {
        logger.info("get user fot principal: [{}]", principal)
        return userRepository.findById(principal.name).orElseThrow {
            Exception("User with id [${principal.name}] not found")
        }
    }


}

