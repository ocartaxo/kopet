package br.com.ocartaxo.kopet.api.domain.tutor

import org.springframework.data.jpa.repository.JpaRepository

interface TutorsRepository: JpaRepository<Tutor, Int>{

}
