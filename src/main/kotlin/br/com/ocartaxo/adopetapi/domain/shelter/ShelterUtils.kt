package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.location.toDTO
import br.com.ocartaxo.adopetapi.domain.location.toEntity
import br.com.ocartaxo.adopetapi.domain.user.Profile
import br.com.ocartaxo.adopetapi.domain.user.ProfileType
import br.com.ocartaxo.adopetapi.domain.user.User

fun ShelterRequest.toEntity() = Shelter(
    name = this.name,
    phone = this.phone,
    location = this.location.toEntity(),
    user = User(email = this.email, password = this.password, profile = Profile(type = ProfileType.SHELTER))
)


fun Shelter.toDTO() = ShelterResponse(
    id = this.id!!,
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
    this.user.email = newInfo.email ?: this.user.email
    this.user.password = newInfo.password ?: this.user.password
    this.name = newInfo.name ?: this.name
    this.phone = newInfo.phone ?: this.phone
    this.name = newInfo.name ?: this.name
    this.location = newInfo.location?.toEntity() ?: this.location
}
