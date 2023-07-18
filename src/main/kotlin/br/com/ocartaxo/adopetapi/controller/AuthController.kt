package br.com.ocartaxo.adopetapi.controller

import br.com.ocartaxo.adopetapi.domain.user.AuthenticationRequest
import br.com.ocartaxo.adopetapi.domain.user.AuthenticationResponse
import br.com.ocartaxo.adopetapi.domain.user.AuthenticationService
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name="Autenticação")
@RestController
@RequestMapping("/api/authenticate")
class AuthController(
    private val service: AuthenticationService
) {

    @PostMapping
    fun login(@RequestBody @Valid bodyRequest: AuthenticationRequest): ResponseEntity<AuthenticationResponse> {
        return ResponseEntity.ok().body(service.authenticate(bodyRequest))
    }


}