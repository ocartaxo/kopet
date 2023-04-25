package br.com.ocartaxo.adopetapi.domain.tutor

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TutorService(
    private val repository: TutorsRepository
) {
    fun register(request: TutorRequest): TutorResponse {
        val t: Tutor = request.toEntity()
        repository.save(t)
        return t.toDTO()
    }

    fun list(pageable: Pageable): Page<TutorSummaryResponse> =
        repository.findAll(pageable).map(Tutor::toSummaryResponse)

    fun show(id: Int): TutorResponse = repository.findById(id)
        .orElseThrow { IllegalArgumentException("Tutor de id $id não está cadastrado na base!") }.toDTO()

    fun update(request: TutorUpdateRequest): TutorResponse {
        val t: Tutor = repository.findById(request.id).orElseThrow()
        t.update(request)
        return t.toDTO()
    }

    fun delete(id: Int) = repository.deleteById(id)


}
