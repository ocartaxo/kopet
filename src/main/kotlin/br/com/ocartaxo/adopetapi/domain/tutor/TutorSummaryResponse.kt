package br.com.ocartaxo.adopetapi.domain.tutor

import com.fasterxml.jackson.annotation.JsonProperty

data class TutorSummaryResponse(
    val id: Int,
    val email: String,
    @JsonProperty("nome")
    val name: String
)
