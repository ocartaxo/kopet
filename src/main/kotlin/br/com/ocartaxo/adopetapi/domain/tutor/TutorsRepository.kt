package br.com.ocartaxo.adopetapi.domain.tutor

import org.springframework.data.jpa.repository.JpaRepository

interface TutorsRepository: JpaRepository<Tutor, Int>{

}
