package br.com.ocartaxo.adopetapi.domain.pet

import br.com.ocartaxo.adopetapi.domain.shelter.ShelterRepository
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class PetService(
    private val petRepository: PetRepository,
    private val shelterRepository: ShelterRepository
) {
    fun register(request: PetRequest): PetResponse {
        val shelter = shelterRepository.findById(request.shelterId).orElseThrow { RuntimeException() }
        val pet = request.toEntity(shelter)

        return petRepository.save(pet).toDTO()
    }

    fun list(pageable: Pageable) = petRepository.findAllByAdoptedIsFalse(pageable).map(Pet::toSummaryDTO)
    fun show(id: Int) = petRepository.findById(id).orElseThrow { RuntimeException("Pet de id '$id' não encontrado!") }.toDTO()
    fun update(request: PetUpdateRequest): PetResponse {
        val pet = petRepository.findById(request.id)
            .orElseThrow { RuntimeException("Pet não encontrado!") }

        val shelter = shelterRepository.findByIdOrNull(request.shelterId)

        pet.update(request, shelter)

        return pet.toDTO()
    }


}