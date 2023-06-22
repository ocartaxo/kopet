package br.com.ocartaxo.adopetapi.infra.security

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


@Configuration
@EnableWebSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    private val allowedURIs = arrayOf(
        "/v3/api-docs/**",
        "/swagger-ui.html",
        "/swagger-ui/**",
        "/authenticate/**"
    )

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf().disable()
            .sessionManagement { session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .authorizeHttpRequests { request ->
                request.requestMatchers(*allowedURIs).permitAll()
                request.requestMatchers(HttpMethod.POST, "/abrigos").hasRole("ABRIGO")
                request.requestMatchers(HttpMethod.POST, "/tutores").hasRole("TUTORES")

            }
            .authorizeHttpRequests().anyRequest().authenticated().and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter::class.java)


        return http.build()
    }

}