package br.com.ocartaxo.kopet.api.domain.pet

import com.fasterxml.jackson.annotation.JsonProperty

data class PetSummaryResponse(
    val id: Int,
    @JsonProperty("nome")
    val name: String,
)
