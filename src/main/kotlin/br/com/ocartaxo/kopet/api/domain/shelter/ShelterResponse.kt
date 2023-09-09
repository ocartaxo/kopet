package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.ocartaxo.kopet.api.domain.location.LocationDTO
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ShelterResponse(
    val id: Int,
    @JsonProperty("telefone")
    val phone: String,
    val email: String,
    @JsonProperty("nome")
    val name: String,
    @JsonProperty("localizacao")
    val location: LocationDTO,
    @JsonProperty("data_criacao")
    val createdOn: LocalDateTime
)
