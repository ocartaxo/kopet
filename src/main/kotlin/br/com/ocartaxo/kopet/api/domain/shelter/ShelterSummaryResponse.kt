package br.com.ocartaxo.kopet.api.domain.shelter

import com.fasterxml.jackson.annotation.JsonProperty

class ShelterSummaryResponse(
    id: Int,
    @JsonProperty("nome")
    name: String
)
