package br.com.ocartaxo.kopet.api.infra.error

import org.springframework.security.authentication.BadCredentialsException

data class ValidationException(
    override val message: String
): BadCredentialsException(message)
