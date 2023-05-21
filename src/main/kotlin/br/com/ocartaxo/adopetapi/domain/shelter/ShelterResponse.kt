package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.location.LocationDTO
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class ShelterResponse(
    val id: Int,
    @JsonProperty("phone")
    val phone: String,
    @JsonProperty("nome")
    val name: String,
    @JsonProperty("localizacao")
    val location: LocationDTO,
    @JsonProperty("data_criacao")
    val createdOn: LocalDateTime
)
