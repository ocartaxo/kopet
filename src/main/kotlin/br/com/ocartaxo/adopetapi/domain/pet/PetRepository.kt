package br.com.ocartaxo.adopetapi.domain.pet

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PetRepository: JpaRepository<Pet, Int> {
    fun findAllByAdoptedIsFalse(pageable: Pageable): Page<Pet>

}
