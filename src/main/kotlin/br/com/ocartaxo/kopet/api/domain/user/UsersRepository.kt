package br.com.ocartaxo.kopet.api.domain.user

import org.springframework.data.jpa.repository.JpaRepository
import java.util.Optional

interface UsersRepository: JpaRepository<User, Int> {
    fun findUserByEmailAndPassword(email: String, password: String): Optional<User>
    fun findUserByEmail(email: String): Optional<User>

}