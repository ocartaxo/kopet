package br.com.ocartaxo.adopetapi.domain.tutor

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class TutorRequest(
    @NotBlank(message = "Campo não pode ser vazio!")
    @JsonProperty("nome")
    val name: String,
    @NotBlank(message = "Campo não pode ser vazio!")
    @Email(message = "Padrão inválido")
    val email: String,
    @NotBlank(message = "Campo não pode ser vazio!", )
    @JsonProperty("senha")
    val password: String
)