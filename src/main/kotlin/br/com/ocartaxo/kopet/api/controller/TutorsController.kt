package br.com.ocartaxo.kopet.api.controller



import br.com.ocartaxo.kopet.api.domain.tutor.*
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
@RequestMapping("/api/tutores")
class TutorsController(private val service: TutorService) {

    @PostMapping
    fun register(
        @RequestBody @Valid request: TutorRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<TutorResponse>{

        val response = service.register(request)
        val uri = builder.path("/api/tutores/${response.id}").build().toUri()

        return ResponseEntity.created(uri).body(response)
    }

    @GetMapping
    fun list(
        @PageableDefault(size=10, sort=["name"], direction= Sort.Direction.ASC) pageable: Pageable
    ) = ResponseEntity.ok(service.list(pageable))

    @GetMapping("/{id}")
    fun show(@PathVariable id: Int) = service.show(id)

    @RequestMapping(method = [RequestMethod.PUT, RequestMethod.PATCH])
    fun update(request: TutorUpdateRequest) = service.update(request)

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Int): ResponseEntity<Unit> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }

}