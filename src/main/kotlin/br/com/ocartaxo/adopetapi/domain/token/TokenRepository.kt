package br.com.ocartaxo.adopetapi.domain.token

import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository: JpaRepository<Token, Int> {

}
