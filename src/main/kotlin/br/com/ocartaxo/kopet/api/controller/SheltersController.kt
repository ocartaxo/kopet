package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.kopet.api.domain.shelter.ShelterRequest
import br.com.ocartaxo.kopet.api.domain.shelter.ShelterService
import br.com.ocartaxo.kopet.api.domain.shelter.ShelterSummaryResponse
import br.com.ocartaxo.kopet.api.domain.shelter.ShelterUpdateRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import jakarta.validation.Valid
import org.springframework.data.domain.Pageable
import org.springframework.data.domain.Sort
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

@RestController
@RequestMapping("/apí/abrigo", produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "Abrigos", description = "API para abrigos")
class SheltersController(private val service: ShelterService) {

    @Operation(
        summary = "Lista informações dos abrigos",
        description = "Lista informações sumarizadas dos abrigos cadastrados de forma paginada"
    )
    @GetMapping
    fun list(
        @PageableDefault(size=10, sort=["name"], direction = Sort.Direction.ASC) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))


    @Operation(
        summary = "Retorna informações de um abrigo",
        description = "Retorna informações detalhadas do abrigo de id informado"
    )
    @GetMapping("/{id}")
    fun show(@PathVariable id: Int) = ResponseEntity.ok(service.show(id))


    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    fun update(@RequestBody @Valid request: ShelterUpdateRequest) = ResponseEntity.ok(service.update(request))

    @Operation(
        summary = "Remove abrigo",
        description = "Remove o abrigo de id informado."
    )
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}