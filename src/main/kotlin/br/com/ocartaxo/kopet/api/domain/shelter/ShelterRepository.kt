package br.com.ocartaxo.kopet.api.domain.shelter

import org.springframework.data.jpa.repository.JpaRepository

interface ShelterRepository: JpaRepository<Shelter, Int> {

}
