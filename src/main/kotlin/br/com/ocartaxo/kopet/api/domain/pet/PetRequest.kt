package br.com.ocartaxo.kopet.api.domain.pet

import com.fasterxml.jackson.annotation.JsonProperty
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class PetRequest(
    @NotBlank
    @JsonProperty("nome")
    val name: String,
    @NotBlank
    @JsonProperty("idade")
    val age: String,
    @NotBlank
    @JsonProperty("descricao")
    val description: String,
    @NotBlank
    @JsonProperty("imagem")
    val image: String,
    @NotNull
    @JsonProperty("abrigo_id")
    val shelterId: Int,
    @NotBlank
    @JsonProperty("especie")
    val specie: PetSpecie,
    @JsonProperty("tamanho")
    val size: PetSize? = null
)
