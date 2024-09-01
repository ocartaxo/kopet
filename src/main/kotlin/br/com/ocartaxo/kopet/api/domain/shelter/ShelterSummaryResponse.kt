package br.com.ocartaxo.kopet.api.domain.shelter

import com.fasterxml.jackson.annotation.JsonProperty

data class ShelterSummaryResponse(
    val id: Int,
    @JsonProperty("nome")
    val name: String
)
