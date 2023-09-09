package br.com.ocartaxo.kopet.api.domain.shelter

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository

interface ShelterRepository: JpaRepository<Shelter, Int> {
    fun findAllByEnabledIsTrue(pageable: Pageable): Page<Shelter>

}
