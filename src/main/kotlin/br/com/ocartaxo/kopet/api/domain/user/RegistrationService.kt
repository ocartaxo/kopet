package br.com.ocartaxo.kopet.api.domain.user

import org.slf4j.LoggerFactory
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class RegistrationService(
    private val usersRepository: UsersRepository,
    private val passwordEncoder: PasswordEncoder
) {

    private val logger = LoggerFactory.getLogger(this.javaClass)

    fun register(request: RegisterRequest): User {
        logger.info("I=Criando o usuário ${request.username}")
        request.password = passwordEncoder.encode(request.password)

         return usersRepository.save(request.toEntity())
    }
}
