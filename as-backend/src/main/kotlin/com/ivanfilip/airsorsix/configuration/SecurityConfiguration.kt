package com.ivanfilip.airsorsix.configuration

import com.ivanfilip.airsorsix.repository.UserRepository
import com.ivanfilip.airsorsix.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import javax.servlet.http.HttpServletResponse
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.config.http.SessionCreationPolicy


@Configuration
@EnableWebSecurity
class SecurityConfiguration(val userService: UserService,
                            val encoder: PasswordEncoderConfiguration,
                            val userRepository: UserRepository) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userService)
                .passwordEncoder(encoder.passwordEncoder())
    }


    override fun configure(http: HttpSecurity) {
        http
                .csrf()
                .disable()
                .httpBasic().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED).and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .authorizeRequests()
                .antMatchers("/api/user/*").hasRole("USER")
                .antMatchers("/api/create/*").hasRole("ADMIN")
                .antMatchers("/api/**").permitAll()
                .anyRequest().fullyAuthenticated()
                .and()
                .oauth2Login()
                .loginProcessingUrl("/api/public/code/github")
                .authorizationEndpoint()
                .baseUri("/api/public/login")
                .and()
                .and()
                .logout()
                .logoutUrl("/api/logout")
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/home")
                .deleteCookies("JSESSIONID")
                .and().oauth2Client()

    }

    @Bean
    public override fun userDetailsService(): UserDetailsService {
        return UserService(this.userRepository, encoder)
    }


    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { _, response, _ ->
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied")
        }
    }

}
