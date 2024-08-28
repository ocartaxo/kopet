package br.com.ocartaxo.kopet.api.domain.user

data class AuthenticationResponse(
    val accessToken: String,
    val expirationTime: Long,
    val refreshToken: String
)
