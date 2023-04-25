package br.com.ocartaxo.adopetapi.domain.adoption

import org.springframework.data.jpa.repository.JpaRepository

interface AdoptionsRepository: JpaRepository<Adoption, Int> {

}
