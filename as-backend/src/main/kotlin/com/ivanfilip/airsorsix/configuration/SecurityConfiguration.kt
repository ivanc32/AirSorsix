package com.ivanfilip.airsorsix.configuration

import com.ivanfilip.airsorsix.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
@EnableWebSecurity
class SecurityConfiguration (val userService: UserService,
                 val encoder: PasswordEncoderConfiguration) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder.passwordEncoder())
    }

    override fun configure(http: HttpSecurity) {
        http.authorizeRequests()
                    .antMatchers("/api/admin/create/**")
                    .authenticated()
                    .anyRequest()
                    .permitAll()
                    .and()
                    .formLogin()
                    .permitAll()
                    .and()
                    .csrf().disable()
    }
}