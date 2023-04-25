package br.com.ocartaxo.adopetapi.domain.tutor

import br.com.ocartaxo.adopetapi.domain.location.LocationDTO
import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class TutorResponse(
    val id: Int,
    @JsonProperty("nome")
    val name: String,
    val email: String,
    @JsonProperty("telefone")
    val phone: String?,
    @JsonProperty("localizacao")
    val location: LocationDTO?,
    @JsonProperty("resumo")
    val about: String?,
    @JsonProperty("foto")
    val image: String?,
    val createdOn: LocalDateTime?
)
