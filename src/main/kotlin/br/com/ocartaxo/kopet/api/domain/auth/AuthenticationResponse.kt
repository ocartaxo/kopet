package br.com.ocartaxo.kopet.api.domain.auth

data class AuthenticationResponse(
    val accessToken: String,
    val expirationTime: Long,
    val refreshToken: String
)
