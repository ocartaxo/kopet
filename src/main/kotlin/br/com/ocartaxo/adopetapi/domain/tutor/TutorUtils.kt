package br.com.ocartaxo.adopetapi.domain.tutor

import br.com.ocartaxo.adopetapi.domain.location.toDTO

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