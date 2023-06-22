package br.com.ocartaxo.adopetapi.controller

import br.com.ocartaxo.adopetapi.domain.shelter.ShelterResponse
import br.com.ocartaxo.adopetapi.domain.shelter.ShelterRequest
import br.com.ocartaxo.adopetapi.domain.shelter.ShelterService
import br.com.ocartaxo.adopetapi.domain.shelter.ShelterUpdateRequest
import io.swagger.v3.oas.annotations.security.SecurityRequirement
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/abrigos")
@SecurityRequirement(name = "bearer-key")
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

    @GetMapping
    @Cacheable("abrigos")
    fun list(
        @PageableDefault(size=10, sort=["name"], direction = Sort.Direction.ASC) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int) = ResponseEntity.ok(service.show(id))

    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    @Transactional
    fun update(@RequestBody @Valid request: ShelterUpdateRequest) = ResponseEntity.ok(service.update(request))

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}