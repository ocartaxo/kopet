package br.com.ocartaxo.adopetapi.domain.pet

import br.com.ocartaxo.adopetapi.domain.shelter.ShelterRepository
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


}