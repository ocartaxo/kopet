package br.com.ocartaxo.adopetapi.domain.user

data class AuthenticationResponse(
    val accessToken: String,
    val expirationTime: Long,
    val refreshToken: String
)
