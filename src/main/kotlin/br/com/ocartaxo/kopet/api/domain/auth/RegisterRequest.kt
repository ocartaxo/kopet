package br.com.ocartaxo.kopet.api.domain.auth

import br.com.ocartaxo.kopet.api.domain.user.Role

data class RegisterRequest(
    val username: String,
    var password: String,
    val role: Role
)
