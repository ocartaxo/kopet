package br.com.ocartaxo.adopetapi.domain.adoption

import com.fasterxml.jackson.annotation.JsonProperty
import java.time.LocalDateTime

data class AdoptionResponse(
    val id: Int,
    @JsonProperty("data")
    val date: LocalDateTime,
    @JsonProperty("tutor")
    val tutorName: String,
    @JsonProperty("pet")
    val petName: String
)
