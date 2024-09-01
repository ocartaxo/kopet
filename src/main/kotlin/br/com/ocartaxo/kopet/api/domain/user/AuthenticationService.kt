package br.com.ocartaxo.kopet.api.domain.user

import br.com.ocartaxo.kopet.api.domain.token.Token
import br.com.ocartaxo.kopet.api.domain.token.TokenRepository
import br.com.ocartaxo.kopet.api.infra.security.JwtService
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val jwtService: JwtService,
    private val tokenRepository: TokenRepository,
) {
    private val logger = LoggerFactory.getLogger(this.javaClass)
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

    private fun saveToken(user: User, jwtToken: String) {
        val token = Token(user = user, tokenValue = jwtToken)
        user.addToken(token)
        tokenRepository.save(token)
    }


}