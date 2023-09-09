package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.ocartaxo.api.domain.location.LocationDTO
import com.fasterxml.jackson.annotation.JsonProperty

data class ShelterUpdateRequest(
    val id: Int,
    val email: String? = null,
    @JsonProperty("telefone")
    val phone: String? = null,
    @JsonProperty("nome")
    val name: String? = null,
    @JsonProperty("localizacao")
    val location: LocationDTO? = null
)
