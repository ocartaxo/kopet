package br.com.ocartaxo.adopetapi.infra.error

import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.AccessDeniedException
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
    ): ResponseEntity<Any> = ResponseEntity.badRequest().body(
        ErrorResponse(
            mensagem = ex.message,
            path = request.requestURI
        )
    )

    @ExceptionHandler(UsernameNotFoundException::class)
    fun handleUsernameNotFoundException(ex: UsernameNotFoundException): ResponseEntity<Unit> {
        return ResponseEntity.notFound().build()
    }

    @ExceptionHandler(AccessDeniedException::class)
    fun handleAccessDeniedException(
        ex: AccessDeniedException,
        request: HttpServletRequest
    ): ResponseEntity<Any> = ResponseEntity.status(HttpStatus.FORBIDDEN).body(object {
        val message = "Acesso não autorizado"
        val uri = request.requestURI
        val status = HttpStatus.INTERNAL_SERVER_ERROR.value()
        val timestamp = LocalDateTime.now()
    })

    @ExceptionHandler(RuntimeException::class)
    fun handleRuntimeException(
        ex: RuntimeException,
        request: HttpServletRequest
    ): ResponseEntity<Any> = ResponseEntity.internalServerError().body(
        object {
            val message = "Erro não identificado!"
            val errorMsg = ex.localizedMessage
            val uri = request.requestURI
            val status = HttpStatus.INTERNAL_SERVER_ERROR.value()
            val timestamp = LocalDateTime.now()
        }
    )

    data class ErrorResponse(
        val mensagem: String?,
        val timestamp: LocalDateTime = LocalDateTime.now(),
        val path: String
    )

}

