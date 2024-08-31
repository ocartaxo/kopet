package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.ocartaxo.kopet.api.domain.location.toDTO
import br.com.ocartaxo.kopet.api.domain.location.toEntity
import br.com.ocartaxo.kopet.api.domain.user.RegisterRequest
import br.com.ocartaxo.kopet.api.domain.user.Role
import br.com.ocartaxo.kopet.api.domain.user.User
import java.time.LocalDateTime

fun ShelterRequest.toEntity(user: User) = Shelter(
    user = user,
    name = this.name,
    phone = this.phone,
    location = this.location.toEntity()
)

fun Shelter.toDTO() = ShelterResponse(
    id = this.id!!,
    email = this.user.email,
    phone = this.phone,
    name = this.name,
    location = this.location.toDTO(),
    createdOn = this.createdOn
)

fun ShelterRequest.toRegisterRequest() = RegisterRequest(
    username = this.email,
    password = this.password,
    role = Role.SHELTER
)

fun Shelter.toSummaryDTO() = ShelterSummaryResponse(
    id = this.id!!,
    name = this.name
)

fun Shelter.update(newInfo: ShelterUpdateRequest) {
    this.user.email = newInfo.email ?: this.user.email
    this.phone = newInfo.phone ?: this.phone
    this.name = newInfo.name ?: this.name
    this.location = newInfo.location?.toEntity() ?: this.location
    this.updatedOn = LocalDateTime.now()
}

fun Shelter.disable() {
    this.enabled = false
}