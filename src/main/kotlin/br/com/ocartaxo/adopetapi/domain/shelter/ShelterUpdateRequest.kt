package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.location.LocationDTO
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
