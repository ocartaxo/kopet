package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.ShelterResponse
import br.com.ocartaxo.adopetapi.domain.location.toDTO
import br.com.ocartaxo.adopetapi.domain.location.toEntity

fun ShelterRequest.toEntity() = Shelter(
    name = this.name,
    email = this.email,
    phone = this.phone,
    location = this.location.toEntity()
)

fun Shelter.toDTO() = ShelterResponse(
    id = this.id!!,
    email = this.email,
    phone = this.phone,
    name = this.name,
    location = this.location.toDTO(),
    createdOn = this.createdOn
)

fun Shelter.toSummaryDTO() = ShelterSummaryResponse(
    id = this.id!!,
    name = this.name
)

fun Shelter.update(newData: ShelterUpdateRequest) {
    this.email = newData.email ?: this.email
    this.phone = newData.phone ?: this.phone
    this.name = newData.name ?: this.name
    this.location = newData.location?.toEntity() ?: this.location
}