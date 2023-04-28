package br.com.ocartaxo.adopetapi.domain.shelter

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

fun Shelter.update(newInfo: ShelterUpdateRequest) {
    this.email = newInfo.email ?: this.email
    this.phone = newInfo.phone ?: this.phone
    this.name = newInfo.name ?: this.name
    this.location = newInfo.location?.toEntity() ?: this.location
}
