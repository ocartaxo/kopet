package br.com.ocartaxo.kopet.api.domain.location

import jakarta.persistence.Embeddable
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated

@Embeddable
data class Location(
    var address: String,
    var number: Int,
    var district: String,
    var city: String,
    @Enumerated(value = EnumType.STRING)
    var state: States,
    var zipCode: String
){
    override fun toString(): String {
        return "${address}, ${number} - ${district}, ${city} - ${state}, ${zipCode}"
    }
}
