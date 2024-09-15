package br.com.ocartaxo.kopet.api.infra.annotations

import jakarta.validation.Constraint


@Constraint(validatedBy = [PhoneNumberValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class Phone(
  val message: String = "Número de telefone inválido!"
){}
