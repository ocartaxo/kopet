package br.com.ocartaxo.kopet.api.domain.tutor

import br.com.ocartaxo.api.domain.location.LocationDTO
import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class TutorUpdateRequest(
    @NotNull
    val id: Int,
    @JsonProperty("nome")
    val name: String? = null,
    val email: String? = null,
    @JsonProperty("senha")
    val password: String? = null,
    @JsonProperty("localizacao")
    val location: LocationDTO? = null,
    @JsonProperty("telefone")
    val phone: String? = null,
    @JsonProperty("sobre")
    val about: String? = null,
    @JsonProperty("foto")
    val image: String? = null
)
