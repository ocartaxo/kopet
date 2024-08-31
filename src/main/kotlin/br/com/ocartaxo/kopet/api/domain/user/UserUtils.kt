package br.com.ocartaxo.kopet.api.domain.user

fun RegisterRequest.toEntity() = User(
    email = this.username,
    password = this.password,
    role = this.role
)