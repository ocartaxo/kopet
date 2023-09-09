package br.com.ocartaxo.kopet.api.domain.location

import com.fasterxml.jackson.annotation.JsonProperty

data class LocationDTO(
    @JsonProperty("endereco")
    val address: String,
    @JsonProperty("numero")
    val number: Int,
    @JsonProperty("bairro")
    val district: String,
    @JsonProperty("cidade")
    val city: String,
    @JsonProperty("estado")
    val state: States,
    @JsonProperty("cep")
    val zipCode: String
) {
    override fun toString(): String {
        return "$address, $number - $district, $city - $state, $zipCode"
    }


}