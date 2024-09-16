package br.com.ocartaxo.kopet.api.domain.auth

import br.com.ocartaxo.kopet.api.domain.token.Token
import br.com.ocartaxo.kopet.api.domain.token.TokenRepository
import br.com.ocartaxo.kopet.api.domain.user.Role
import br.com.ocartaxo.kopet.api.domain.user.User
import br.com.ocartaxo.kopet.api.domain.user.UsersRepository
import br.com.ocartaxo.kopet.api.infra.security.JwtService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val tokenRepository: TokenRepository,
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService

) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    @Transactional
    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        logger.info("I=Autenticando o usuário ${request.username}")

        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )

       val user = authentication.principal as User

        val jwtToken = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)

        user.revokeTokens()
        saveToken(user, jwtToken)

        SecurityContextHolder.getContext().authentication = authentication

        return AuthenticationResponse(
            accessToken = jwtToken,
            expirationTime = jwtService.extractExpirationTime(jwtToken),
            refreshToken = refreshToken
        )

    }

    @Transactional
    fun register(request: RegisterRequest): AuthenticationResponse? {
        logger.info("I=Cadastrando usuário ${request.username}")

        val user  = User(
            email = request.username,
            password = passwordEncoder.encode(request.password),
            role = request.role
        )

        usersRepository.save(user)

        val jwtToken = jwtService.generateToken(user)
        val refreshToken = jwtService.generateRefreshToken(user)
        saveToken(user, jwtToken)
        return AuthenticationResponse(
            accessToken = jwtToken,
            refreshToken = refreshToken,
            expirationTime = jwtService.extractExpirationTime(jwtToken)
        )
    }

    private fun saveToken(user: User, jwtToken: String) {
        val token = Token(user = user, tokenValue = jwtToken)
        user.addToken(token)
        tokenRepository.save(token)
    }

}