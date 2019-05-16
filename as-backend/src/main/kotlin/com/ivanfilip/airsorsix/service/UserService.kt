package com.ivanfilip.airsorsix.service

import com.ivanfilip.airsorsix.api.CreateUserRequest
import com.ivanfilip.airsorsix.configuration.PasswordEncoderConfiguration
import com.ivanfilip.airsorsix.domain.User
import com.ivanfilip.airsorsix.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service
import java.lang.Exception

@Service
class UserService(val userRepository: UserRepository,
                  val encoder: PasswordEncoderConfiguration):UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
            userRepository.findUserByUsername(username) ?: throw Exception("Username does not exist")

    fun addNewUser(userDto: CreateUserRequest): User =
            userRepository.save(User(username = userDto.username,
                    password = encoder.passwordEncoder().encode(userDto.password),
                    role = userDto.role))

    fun findUserById(id:String): User =
            userRepository.findByIdOrNull(id) ?: throw Exception("there is no user with such Id")

}

