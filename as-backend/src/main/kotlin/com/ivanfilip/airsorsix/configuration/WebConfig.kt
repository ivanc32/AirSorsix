/**package com.ivanfilip.airsorsix.configuration;

import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter

@Configuration
class WebConfig : WebSecurityConfigurerAdapter() {

    override fun configure(http: HttpSecurity?) {
        http?.let {
            it.authorizeRequests()
                    .antMatchers("/api/create/**")
                    .hasRole("ADMIN")
                    .antMatchers("/api/reservation/**")
                    .hasRole("USER")
                    .antMatchers("/api/**")
                    .permitAll()
        }
    }
}
**/