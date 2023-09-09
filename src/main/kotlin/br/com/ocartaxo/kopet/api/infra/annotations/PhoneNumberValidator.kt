package br.com.alura.challenge.adopet.infra.annotations

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.util.regex.Pattern


object PhoneNumberValidator: ConstraintValidator<Phone, String> {

    private val pattern = Pattern.compile("^\\([1-9]{2}\\) (?:[2-8]|9[1-9])[0-9]{3}\\-[0-9]{4}\$").toRegex()

    override fun isValid(value: String, context: ConstraintValidatorContext?): Boolean {

        pattern.matches(value)
        TODO("Not yet implemented")
    }
}