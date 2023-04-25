package br.com.ocartaxo.adopetapi.domain.adoption

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class AdoptionRequest(
    @NotNull
    @JsonProperty("pet_id")
    val petId: Int,
    @NotNull
    @JsonProperty("tutor_id")
    val tutorId: Int
)
