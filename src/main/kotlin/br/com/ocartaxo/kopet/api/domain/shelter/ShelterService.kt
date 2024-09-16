package br.com.ocartaxo.kopet.api.domain.shelter

import jakarta.transaction.Transactional
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShelterService(
    private val shelterRepository: ShelterRepository,
) {

    @Cacheable("abrigos")
    fun list(pageable: Pageable) = shelterRepository.findAll(pageable).map(Shelter::toSummaryDTO)
    @Cacheable("abrigo_por_id")
    fun show(id: Int) = shelterRepository.findById(id)
        .orElseThrow { IllegalArgumentException("Abrigo de id $id não está cadastrado!") }.toDTO()

    @Transactional
    fun update(request: ShelterUpdateRequest): ShelterResponse {
        val shelter = shelterRepository.findById(request.id)
            .orElseThrow { IllegalArgumentException("Abrigo não encontrado!") }

        shelter.update(request)

        return shelter.toDTO()
    }

    @Transactional
    fun delete(id: Int) = shelterRepository.deleteById(id)


}
