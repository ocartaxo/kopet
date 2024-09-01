package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.ocartaxo.kopet.api.domain.user.RegistrationService
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class ShelterService(
    private val shelterRepository: ShelterRepository,
    private val registrationService: RegistrationService,
) {
    fun register(request: ShelterRequest): ShelterSummaryResponse {
        val shelter = request.toEntity(registrationService.register(request.toRegisterRequest()))
        return shelter.toSummaryDTO()
    }

    fun list(pageable: Pageable) = shelterRepository.findAll(pageable).map(Shelter::toSummaryDTO)
    fun show(id: Int) = shelterRepository.findById(id)
        .orElseThrow { IllegalArgumentException("Abrigo de id $id não está cadastrado!") }.toDTO()

    fun update(request: ShelterUpdateRequest): ShelterResponse {
        val shelter = shelterRepository.findById(request.id)
            .orElseThrow { IllegalArgumentException("Abrigo não encontrado!") }

        shelter.update(request)

        return shelter.toDTO()
    }

    fun delete(id: Int) = shelterRepository.deleteById(id)


}
