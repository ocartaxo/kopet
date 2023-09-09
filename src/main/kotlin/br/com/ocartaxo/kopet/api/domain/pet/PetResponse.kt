package br.com.ocartaxo.kopet.api.domain.pet

import com.fasterxml.jackson.annotation.JsonProperty

class PetResponse(
    val id: Int,
    @JsonProperty("abrigo_id")
    val shelter: Int,
    @JsonProperty("nome")
    val name: String,
    @JsonProperty("idade")
    val age: String,
    @JsonProperty("descricao")
    val description: String,
    @JsonProperty("espécie")
    val specie: String,
    @JsonProperty("adotado")
    val adopeted: Boolean,
    @JsonProperty("tamanho")
    val size: PetSize?,
    @JsonProperty("imagem")
    val image: String,
) {

}
