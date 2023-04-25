package br.com.ocartaxo.adopetapi.domain.tutor

import org.springframework.stereotype.Service

@Service
class TutorService(private val repository: TutorsRepository) {
    fun register(request: TutorRequest): TutorResponse {
        val t: Tutor = request.toEntity()
        repository.save(t)
        return t.toDTO()
    }



}
