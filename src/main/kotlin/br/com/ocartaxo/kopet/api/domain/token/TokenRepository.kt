package br.com.ocartaxo.kopet.api.domain.token

import org.springframework.data.jpa.repository.JpaRepository

interface TokenRepository: JpaRepository<Token, Int> {

}
