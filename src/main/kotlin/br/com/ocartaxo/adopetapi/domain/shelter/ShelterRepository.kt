package br.com.ocartaxo.adopetapi.domain.shelter

import org.springframework.data.jpa.repository.JpaRepository

interface ShelterRepository: JpaRepository<Shelter, Int> {

}
