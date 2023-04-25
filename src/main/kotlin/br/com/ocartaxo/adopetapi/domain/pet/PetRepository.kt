package br.com.ocartaxo.adopetapi.domain.pet

import org.springframework.data.jpa.repository.JpaRepository

interface PetRepository: JpaRepository<Pet, Int> {

}
