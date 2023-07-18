package br.com.ocartaxo.adopetapi.controller

import br.com.ocartaxo.adopetapi.domain.adoption.AdoptionRequest
import br.com.ocartaxo.adopetapi.domain.adoption.AdoptionResponse
import br.com.ocartaxo.adopetapi.domain.adoption.AdoptionService
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
@Tag(name = "Adoções")
@RestController
@RequestMapping("/api/adocoes")
@SecurityRequirement(name = "bearer-key")
class AdoptionsController(private val service: AdoptionService) {

    @PreAuthorize("hasAuthority('adocao:registrar')")
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

    @PreAuthorize("hasAuthority('abrigo:deletar')")
    @DeleteMapping
    @Transactional
    fun deleteAdoption(id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}