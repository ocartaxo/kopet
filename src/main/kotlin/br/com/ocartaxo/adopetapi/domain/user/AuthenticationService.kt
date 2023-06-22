package br.com.ocartaxo.adopetapi.domain.user

import br.com.ocartaxo.adopetapi.domain.token.Token
import br.com.ocartaxo.adopetapi.domain.token.TokenRepository
import br.com.ocartaxo.adopetapi.infra.security.JwtService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class AuthenticationService(
    private val authenticationManager: AuthenticationManager,
    private val passwordEncoder: PasswordEncoder,
    private val jwtService: JwtService,
    private val usersRepository: UsersRepository,
    private val tokenRepository: TokenRepository,
) {

    fun authenticate(request: AuthenticationRequest): AuthenticationResponse {
        val authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                request.username,
                request.password
            )
        )


        val user = usersRepository.findByEmail(request.username)
            .orElseThrow { IllegalArgumentException("Login e/ou senha incorretos!") }

        user.password = passwordEncoder.encode(request.password)

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