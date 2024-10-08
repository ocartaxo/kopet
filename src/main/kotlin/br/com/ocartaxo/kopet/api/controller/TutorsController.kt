package br.com.ocartaxo.kopet.api.controller

import br.com.ocartaxo.kopet.api.domain.tutor.TutorService
import br.com.ocartaxo.kopet.api.domain.tutor.TutorUpdateRequest
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.Parameter
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*


@RestController
@RequestMapping("/api/tutores", produces = [MediaType.APPLICATION_JSON_VALUE])
@Tag(name = "Tutores", description = "CRUD para tutores")
class TutorsController(private val service: TutorService) {


    @GetMapping
    @Operation(
        summary = "Lista os tutores.",
        description = "Lista informações sumarizada dos tutores registrados de forma paginada."
    )
    fun list(
        @PageableDefault(size=10) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))

    @GetMapping("/{id}")
    @Operation(summary = "Exibe informações de um tutor")
    fun show(@PathVariable @Parameter(description = "id do tutor") id: Int) = service.show(id)

    @Operation(
        summary = "Atualiza as informações do tutor.",
        description = "Atualiza informações do tutor de id informado.")
    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    fun update(request: TutorUpdateRequest) = service.update(request)

    @Operation(description = "Remove um tutor da base de dados")
    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}