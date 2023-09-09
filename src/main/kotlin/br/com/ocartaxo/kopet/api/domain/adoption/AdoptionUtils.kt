package br.com.ocartaxo.kopet.api.domain.adoption

import br.com.ocartaxo.kopet.api.domain.pet.Pet

fun br.com.ocartaxo.kopet.api.domain.adoption.Adoption.toDTO() =
    br.com.ocartaxo.kopet.api.domain.adoption.AdoptionResponse(
        id = this.id!!,
        date = this.date,
        tutorName = this.tutor.name,
        petName = this.pet.name
    )

fun Pet.isAdopted() = this.adopted

fun Pet.adopt() {
    this.adopted = true
}