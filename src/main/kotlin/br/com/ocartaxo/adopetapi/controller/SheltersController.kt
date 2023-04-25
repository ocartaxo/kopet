package br.com.ocartaxo.adopetapi.controller

import br.com.ocartaxo.adopetapi.domain.ShelterResponse
import br.com.ocartaxo.adopetapi.domain.shelter.ShelterRequest
import br.com.ocartaxo.adopetapi.domain.shelter.ShelterService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.util.UriComponentsBuilder

@RequestMapping("/api/abrigos")
@RestController
class SheltersController(private val service: ShelterService) {

    @PostMapping
    @Transactional
    fun register(
        @RequestBody @Valid request: ShelterRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<ShelterResponse> {
        val response = service.register(request)
        val uri = builder.path("/api/abrigos/${response.id}").build().toUri()
        return ResponseEntity.created(uri).body(response)
    }

}