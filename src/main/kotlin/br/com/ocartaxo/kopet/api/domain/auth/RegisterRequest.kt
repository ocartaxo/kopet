package br.com.ocartaxo.kopet.api.domain.auth

import br.com.ocartaxo.kopet.api.domain.user.Role
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class RegisterRequest(
    @Email
    @JsonProperty("email")
    val username: String,
    @NotBlank
    @JsonProperty("senha")
    var password: String,
    @NotBlank
    @JsonProperty("perfil")
    val role: Role
)
