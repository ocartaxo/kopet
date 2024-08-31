package br.com.ocartaxo.kopet.api.domain.tutor

import br.com.ocartaxo.kopet.api.domain.location.toDTO
import br.com.ocartaxo.kopet.api.domain.location.toEntity
import br.com.ocartaxo.kopet.api.domain.user.RegisterRequest
import br.com.ocartaxo.kopet.api.domain.user.Role
import br.com.ocartaxo.kopet.api.domain.user.User
import java.time.LocalDateTime

fun Tutor.toDTO() = TutorResponse(
    id = this.id!!,
    name = this.name,
    email = this.user.email,
    phone = this.phone,
    location = this.location?.toDTO(),
    about = this.about,
    image = this.image,
    createdOn = this.createdOn,
    updateOn = this.updateOn
)

fun TutorRequest.toRegisterRequest() = RegisterRequest(
    username = this.email,
    password = this.password,
    role = Role.TUTOR
)

fun TutorRequest.toEntity(user: User) = Tutor(
    name = this.name,
    user = user
)

fun Tutor.toSummaryResponse() = TutorSummaryResponse(
    id = this.id!!,
    email = this.user.email,
    name = this.name
)

fun Tutor.update(newData: TutorUpdateRequest) {
    this.name = newData.name ?: this.name
    this.location = newData.location?.toEntity() ?: this.location
    this.phone = newData.phone ?: this.phone
    this.about = newData.about ?: this.about
    this.image = newData.image ?: this.image
    this.updateOn = LocalDateTime.now()
}
