package br.com.ocartaxo.kopet.api.domain.pet

import br.com.ocartaxo.kopet.api.domain.shelter.Shelter

fun PetRequest.toEntity(s: Shelter) = Pet(
    name = this.name,
    age = this.age,
    specie = this.specie,
    description = this.description,
    size = this.size,
    image = this.image,
    shelter = s
)

fun Pet.toDTO() = PetResponse(
    id = this.id!!,
    shelter = this.shelter.id!!,
    name = this.name,
    description = this.description,
    age = this.age,
    specie = this.specie.name,
    size = this.size!!,
    image = this.image,
    adopeted = this.adopted
)

fun Pet.toSummaryDTO() = PetSummaryResponse(
    id = this.id!!,
    name = this.name
)

fun Pet.update(newPetInfo: PetUpdateRequest, shelter: Shelter?) {
    this.age = newPetInfo.age ?: this.age
    this.shelter = shelter ?: this.shelter
    this.name = newPetInfo.name ?: this.name
    this.size = newPetInfo.size ?: this.size
    this.image = newPetInfo.image ?: this.image
    this.specie = newPetInfo.specie ?: this.specie
    this.adopted = newPetInfo.adopted ?: this.adopted
    this.description = newPetInfo.description ?: this.description

}