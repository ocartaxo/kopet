package br.com.ocartaxo.adopetapi.controller

import br.com.ocartaxo.adopetapi.domain.pet.PetRequest
import br.com.ocartaxo.adopetapi.domain.pet.PetService
import jakarta.transaction.Transactional
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
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

}