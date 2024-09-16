package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.kopet.api.domain.shelter.ShelterRequest
import br.com.ocartaxo.kopet.api.domain.shelter.ShelterService
import br.com.ocartaxo.kopet.api.domain.shelter.ShelterSummaryResponse
import br.com.ocartaxo.kopet.api.domain.shelter.ShelterUpdateRequest
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
class SheltersController(private val service: ShelterService) {

    @PostMapping
    fun register(
        @RequestBody @Valid request: ShelterRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<ShelterSummaryResponse> {
        val response = service.register(request)
        val uri = builder.path("/api/abrigos/${response}").build().toUri()
        return ResponseEntity.created(uri).body(response)
    }

    @GetMapping
    fun list(
        @PageableDefault(size=10, sort=["name"], direction = Sort.Direction.ASC) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int) = ResponseEntity.ok(service.show(id))

    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    fun update(@RequestBody @Valid request: ShelterUpdateRequest) = ResponseEntity.ok(service.update(request))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}