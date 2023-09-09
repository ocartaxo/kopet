package br.com.ocartaxo.kopet.api.domain.tutor

import br.com.ocartaxo.api.domain.location.toDTO
import br.com.ocartaxo.api.domain.location.toEntity

fun Tutor.toDTO() = TutorResponse(
    id = this.id!!,
    name = this.name,
    email = this.email,
    phone = this.phone,
    location = this.location?.toDTO(),
    about = this.about,
    image = this.image,
    createdOn = this.createdOn
)

fun TutorRequest.toEntity() = Tutor(
    name = this.name,
    email = this.email,
    password = this.password,
)

fun Tutor.toSummaryResponse() = TutorSummaryResponse(
    id = this.id!!,
    email = this.email,
    name = this.name
)

fun Tutor.update(newData: TutorUpdateRequest) {
    this.name = newData.name ?: this.name
    this.email = newData.email ?: this.email
    this.password = newData.password ?: this.password
    this.location = newData.location?.toEntity() ?: this.location
    this.phone = newData.phone ?: this.phone
    this.about = newData.about ?: this.about
    this.image = newData.image ?: this.image
}
