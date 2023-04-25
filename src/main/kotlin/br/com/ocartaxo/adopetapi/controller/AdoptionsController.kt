package br.com.ocartaxo.adopetapi.controller

import br.com.ocartaxo.adopetapi.domain.adoption.AdoptionRequest
import br.com.ocartaxo.adopetapi.domain.adoption.AdoptionResponse
import br.com.ocartaxo.adopetapi.domain.adoption.AdoptionService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/adocoes")
class AdoptionsController(private val service: AdoptionService) {

    @PostMapping
    @Transactional
    fun adopt(
        @RequestBody @Valid request: AdoptionRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<AdoptionResponse> {
        val adoption = service.adopt(request)
        val uri = builder.path("/api/adocoes/${adoption.id}").build().toUri()
        return ResponseEntity.created(uri).body(adoption)

    }

}