package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.ShelterResponse
import org.springframework.stereotype.Service

@Service
class ShelterService(private val repository: ShelterRepository) {
    fun register(request: ShelterRequest): ShelterResponse {
        val shelter = request.toEntity()
        repository.save(shelter)
        return shelter.toDTO()
    }

}
