package br.com.ocartaxo.adopetapi.domain.pet

import br.com.ocartaxo.adopetapi.domain.shelter.Shelter

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