package com.ivanfilip.airsorsix.domain

import org.hibernate.annotations.GenericGenerator
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(
        @Id
        @GeneratedValue(generator = "uuid")
        @GenericGenerator(name = "uuid", strategy = "uuid2")
        @Column(name = "id", unique = true, nullable = false)
        val id: String = "",

        @Column(name = "username", unique = true)
        private val username: String = "",

        @Column(name = "password")
        private val password: String = "",

        @Column(name = "role")
        val role: String = "ROLE_USER",

        @Column(name = "provider")
        val provider: String = ""

) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> =
            arrayListOf(role).map { SimpleGrantedAuthority(it) }

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = username

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = password

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}
