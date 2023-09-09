package br.com.ocartaxo.kopet.api.infra.error

data class ValidationException(
    override val message: String
): RuntimeException(message)
