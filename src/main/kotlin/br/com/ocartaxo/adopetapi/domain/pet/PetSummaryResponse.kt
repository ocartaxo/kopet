package br.com.ocartaxo.adopetapi.domain.pet

import com.fasterxml.jackson.annotation.JsonProperty

data class PetSummaryResponse(
    val id: Int,
    @JsonProperty("nome")
    val name: String,
)
