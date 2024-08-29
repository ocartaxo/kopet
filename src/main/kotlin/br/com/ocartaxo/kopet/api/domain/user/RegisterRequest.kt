package br.com.ocartaxo.kopet.api.domain.user

data class RegisterRequest(
    val username: String,
    var password: String,
    val role: Role
)
