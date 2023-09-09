package br.com.alura.challenge.adopet.infra.annotations

import jakarta.validation.Constraint


@Constraint(validatedBy = [PhoneNumberValidator::class])
@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@MustBeDocumented
annotation class Phone(
  val message: String = "Número de telefone inválido!"
){}
