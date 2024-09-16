package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.kopet.api.domain.auth.AuthenticationRequest
import br.com.ocartaxo.kopet.api.domain.auth.AuthenticationService
import br.com.ocartaxo.kopet.api.domain.auth.RegisterRequest
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping("/authenticate")
    fun authenticate(@Valid @RequestBody request: AuthenticationRequest) =
        ResponseEntity.ok(authenticationService.authenticate(request))

    @PostMapping("/register")
    fun register(@Valid @RequestBody request: RegisterRequest) =
        ResponseEntity.ok(authenticationService.register(request))
}