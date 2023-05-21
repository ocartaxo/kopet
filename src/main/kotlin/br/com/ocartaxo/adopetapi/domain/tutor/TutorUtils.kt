package br.com.ocartaxo.adopetapi.domain.tutor

import br.com.ocartaxo.adopetapi.domain.location.toDTO
import br.com.ocartaxo.adopetapi.domain.location.toEntity
import br.com.ocartaxo.adopetapi.domain.user.Profile
import br.com.ocartaxo.adopetapi.domain.user.ProfileType
import br.com.ocartaxo.adopetapi.domain.user.User

fun Tutor.toDTO() = TutorResponse(
    id = this.id!!,
    name = this.name,
    email = this.user.email,
    phone = this.phone,
    location = this.location?.toDTO(),
    about = this.about,
    image = this.image,
    createdOn = this.createdOn
)

fun TutorRequest.toEntity() = Tutor(
    name = this.name,
    user = User(email = this.email, password = this.password, profile = Profile(type = ProfileType.TUTOR))
)

fun Tutor.toSummaryResponse() = TutorSummaryResponse(
    id = this.id!!,
    email = this.user.email,
    name = this.name
)

fun Tutor.update(newData: TutorUpdateRequest) {
    this.name = newData.name ?: this.name
    this.user.email = newData.email ?: this.user.email
    this.user.password = newData.password ?: this.user.password
    this.location = newData.location?.toEntity() ?: this.location
    this.phone = newData.phone ?: this.phone
    this.about = newData.about ?: this.about
    this.image = newData.image ?: this.image
}
