package br.com.ocartaxo.adopetapi.domain.adoption

import br.com.ocartaxo.adopetapi.domain.pet.Pet

fun Adoption.toDTO() = AdoptionResponse(
    id = this.id!!,
    date=this.date,
    tutorName = this.tutor.name,
    petName = this.pet.name
)

fun Pet.isAdopted() = this.adopted

fun Pet.adopt() {
    this.adopted = true
}