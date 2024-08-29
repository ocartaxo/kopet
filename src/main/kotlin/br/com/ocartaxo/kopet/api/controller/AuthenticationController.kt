package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.kopet.api.domain.user.AuthenticationRequest
import br.com.ocartaxo.kopet.api.domain.user.AuthenticationService
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth")
class AuthenticationController(
    private val authenticationService: AuthenticationService,
) {

    @PostMapping
    fun authenticate(@Valid @RequestBody request: AuthenticationRequest) = ResponseEntity.ok(authenticationService.authenticate(request))

}