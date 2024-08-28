package br.com.ocartaxo.kopet.api.domain.pet

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface PetsRepository: JpaRepository<Pet, Int> {
    fun findAllByAdoptedIsFalse(pageable: Pageable): Page<Pet>

}
