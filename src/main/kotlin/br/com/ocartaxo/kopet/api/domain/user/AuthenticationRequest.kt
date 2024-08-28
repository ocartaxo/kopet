package br.com.ocartaxo.kopet.api.domain.user

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank

data class AuthenticationRequest(
    @NotBlank
    @JsonProperty("usuario")
    val username: String,
    @NotBlank
    @JsonProperty("senha")
    val password: String
)
