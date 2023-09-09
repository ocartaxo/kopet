package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.alura.challenge.adopet.infra.annotations.Phone
import br.com.ocartaxo.api.domain.location.LocationDTO
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class ShelterRequest(
    @NotBlank
    @JsonProperty("nome")
    val name: String,
    @NotBlank
    @Email
    val email: String,
    @Phone
    @NotBlank
    @JsonProperty("telefone")
    val phone: String,
    @NotNull
    @JsonProperty("localizacao")
    val location: LocationDTO
)
