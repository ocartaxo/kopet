package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.ShelterResponse
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class ShelterService(private val repository: ShelterRepository) {
    fun register(request: ShelterRequest): ShelterResponse {
        val shelter = request.toEntity()
        repository.save(shelter)
        return shelter.toDTO()
    }

    fun list(pageable: Pageable) = repository.findAllByEnabledIsTrue(pageable).map(Shelter::toSummaryDTO)
    fun show(id: Int) = repository.findById(id)
        .orElseThrow { IllegalArgumentException("Abrigo de id $id não está cadastrado!") }.toDTO()

    fun update(request: ShelterUpdateRequest): ShelterResponse {
        val shelter = repository.findById(request.id)
            .orElseThrow { IllegalArgumentException("Abrigo não encontrado!") }

        shelter.update(request)

        return shelter.toDTO()
    }

    fun delete(id: Int) {
        val shelter = repository.findByIdOrNull(id)
        shelter?.disable()
    }

}
