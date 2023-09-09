package br.com.ocartaxo.kopet.api.infra.security

import br.com.ocartaxo.kopet.api.domain.user.UsersRepository
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

typealias AuthConfig = AuthenticationConfiguration

@Configuration
class SecurityBeansConfig(
    private val repository: UsersRepository
) {

    private val logger = LoggerFactory.getLogger(SecurityBeansConfig::class.java)

    @Bean
    fun userDetailsService(): UserDetailsService {

        return UserDetailsService { username ->

            logger.info("Procurando pelo usuário `$username`")
            repository.findByEmail(username).orElseThrow { UsernameNotFoundException(username) }
        }
    }

    @Bean
    fun authenticationProvider(): AuthenticationProvider {
        val authProvider = DaoAuthenticationProvider()
        authProvider.setPasswordEncoder(passwordEncoder())
        authProvider.setUserDetailsService(userDetailsService())
        return authProvider
    }

    @Bean
    fun passwordEncoder(): PasswordEncoder = BCryptPasswordEncoder()

    @Bean
    fun authenticationManager(config: AuthConfig): AuthenticationManager {
        return config.authenticationManager
    }
}