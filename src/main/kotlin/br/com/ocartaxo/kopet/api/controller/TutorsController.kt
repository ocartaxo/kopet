package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.kopet.api.domain.tutor.TutorRequest
import br.com.ocartaxo.kopet.api.domain.tutor.TutorResponse
import br.com.ocartaxo.kopet.api.domain.tutor.TutorService
import br.com.ocartaxo.kopet.api.domain.tutor.TutorUpdateRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
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
@RequestMapping("/api/tutores", produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "Tutores", description = "API para tutores")
class TutorsController(private val service: TutorService) {

    @PostMapping
    @Operation(summary = "Cria um novo tutor")
    fun register(
        @RequestBody @Valid request: TutorRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<TutorResponse>{

        val response = service.register(request)
        val uri = builder.path("/api/tutores/${response.id}").build().toUri()

        return ResponseEntity.created(uri).body(response)
    }


    @GetMapping
    @Operation(summary = "Lista os tutores")
    fun list(
        @PageableDefault(size=10, sort=["name"], direction= Sort.Direction.ASC) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))

    @GetMapping("/{id}")
    @Operation(summary = "Exibe informações de um tutor")
    fun show(@PathVariable @Parameter(description = "id do tutor") id: Int) = service.show(id)

    @Operation(description = "Atualiza informações de um tutor")
    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    fun update(request: TutorUpdateRequest) = service.update(request)

    @Operation(description = "Remove um tutor da base de dados")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}