package com.ivanfilip.airsorsix.configuration

import com.ivanfilip.airsorsix.service.UserService
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.security.web.util.matcher.AntPathRequestMatcher
import javax.servlet.http.HttpServletResponse


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
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint())
                .and()
                .oauth2Login()
                .loginProcessingUrl("/api/login/github")
                .authorizationEndpoint()
                .baseUri("/api/login")
                .and()
                .and()
                .logout()
                .logoutRequestMatcher(AntPathRequestMatcher("/api/logout"))
                .logoutSuccessUrl("http://localhost:4200/home")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .oauth2Client()
                .and()
                .csrf().disable()
    }

    fun authenticationEntryPoint(): AuthenticationEntryPoint {
        return AuthenticationEntryPoint { _, response, _ ->
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Access Denied")
        }
    }

}
