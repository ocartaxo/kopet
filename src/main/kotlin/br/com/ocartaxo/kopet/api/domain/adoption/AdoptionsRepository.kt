package br.com.ocartaxo.kopet.api.domain.adoption

import org.springframework.data.jpa.repository.JpaRepository

interface AdoptionsRepository: JpaRepository<br.com.ocartaxo.kopet.api.domain.adoption.Adoption, Int> {

}
