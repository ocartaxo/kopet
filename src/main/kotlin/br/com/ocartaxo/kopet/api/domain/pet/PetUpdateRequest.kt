package br.com.ocartaxo.kopet.api.domain.pet

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotNull

data class PetUpdateRequest(
    @NotNull
    val id: Int,
    @JsonProperty("abrigo_id")
    val shelterId: Int? = null,
    @JsonProperty("nome")
    val name: String? = null,
    @JsonProperty("espécie")
    val specie: PetSpecie? = null,
    @JsonProperty("idade")
    val age: String? = null,
    @JsonProperty("tamanho")
    val size: PetSize? = null,
    @JsonProperty("imagem")
    val image: String? = null,
    @JsonProperty("descricao")
    val description: String? = null,
    @JsonProperty("adotado")
    val adopted: Boolean? = null
)
