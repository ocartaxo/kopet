package br.com.ocartaxo.adopetapi.infra.error

data class ValidationException(
    override val message: String
): RuntimeException(message)
