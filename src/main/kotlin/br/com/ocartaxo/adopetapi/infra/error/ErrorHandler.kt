package br.com.ocartaxo.adopetapi.infra.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.ResponseEntity
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDateTime

@RestControllerAdvice
class ErrorHandler {

    @ExceptionHandler(ValidationException::class)
    fun handleValidationException(
        ex: ValidationException,
        request: HttpServletRequest
    ): ResponseEntity<Any> = ResponseEntity.badRequest().body(
        ErrorResponse(
            mensagem = ex.message,
            path = request.requestURI
        )
    )

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleIllegalArgumentException(
        ex: IllegalArgumentException,
        request: HttpServletRequest
    ): ResponseEntity<Any> =ResponseEntity.badRequest().body(
        ErrorResponse(
            mensagem = ex.message,
            path = request.requestURI
        )
    )

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    data class ErrorResponse(
        val mensagem: String?,
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val path: String
    )

}

