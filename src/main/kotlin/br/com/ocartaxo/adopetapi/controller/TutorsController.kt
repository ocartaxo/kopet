package br.com.ocartaxo.adopetapi.controller



import br.com.ocartaxo.adopetapi.domain.tutor.TutorRequest
import br.com.ocartaxo.adopetapi.domain.tutor.TutorResponse
import br.com.ocartaxo.adopetapi.domain.tutor.TutorService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder


@RestController
@RequestMapping("/api/tutores")
class TutorsController(private val service: TutorService) {

    @PostMapping
    @Transactional
    fun register(
        @RequestBody @Valid request: TutorRequest,
        builder: UriComponentsBuilder
    ): ResponseEntity<TutorResponse>{

        val response = service.register(request)
        val uri = builder.path("/api/tutores/${response.id}").build().toUri()

        return ResponseEntity.created(uri).body(response)
    }

}