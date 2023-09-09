package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.api.domain.pet.PetRequest
import br.com.ocartaxo.api.domain.pet.PetService
import br.com.ocartaxo.api.domain.pet.PetUpdateRequest
import jakarta.transaction.Transactional
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/api/pets")
class PetsController(private val service: PetService) {

    @PostMapping
    @Transactional
    fun register(
        @RequestBody request: PetRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<Any> {
        val response = service.register(request)
        val uri = builder.path("pets/$response.id").build().toUri()
        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping
    @Cacheable("pets")
    fun list(
        @PageableDefault(size = 10, sort = ["name"], direction = Sort.Direction.ASC) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))

    @GetMapping("/{id}")
    @Cacheable("pets")
    fun show(@PathVariable id: Int) = ResponseEntity.ok(service.show(id))

    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    @Transactional
    fun update(@RequestBody request: PetUpdateRequest) = ResponseEntity.ok(service.update(request))

    @DeleteMapping("/{id}")
    @Transactional
    fun delete(@PathVariable id: Int): ResponseEntity<Unit>{
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}