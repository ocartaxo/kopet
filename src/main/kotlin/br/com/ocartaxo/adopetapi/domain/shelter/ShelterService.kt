package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.ShelterResponse
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShelterService(private val repository: ShelterRepository) {
    fun register(request: ShelterRequest): ShelterResponse {
        val shelter = request.toEntity()
        repository.save(shelter)
        return shelter.toDTO()
    }

    fun list(pageable: Pageable) = repository.findAll(pageable).map(Shelter::toSummaryDTO)
    fun show(id: Int) = repository.findById(id)
        .orElseThrow { IllegalArgumentException("Abrigo de id $id não está cadastrado!") }.toDTO()

}
