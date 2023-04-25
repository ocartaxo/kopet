package br.com.ocartaxo.adopetapi.domain.adoption

import br.com.ocartaxo.adopetapi.domain.pet.PetsRepository
import br.com.ocartaxo.adopetapi.domain.tutor.TutorsRepository
import org.springframework.stereotype.Service

@Service
class AdoptionService(
    private val adoptionsRepository: AdoptionsRepository,
    private val petsRepository: PetsRepository,
    private val tutorsRepository: TutorsRepository
) {
    fun adopt(request: AdoptionRequest): AdoptionResponse {
        val pet = petsRepository.findById(request.petId)
            .orElseThrow { IllegalArgumentException("Pet não encontrado!") }

        if (pet.isAdopted()) {
            throw java.lang.IllegalArgumentException("Pet já adotado!")
        }

        val tutor = tutorsRepository.findById(request.tutorId)
            .orElseThrow { IllegalArgumentException("Tutor não encontrado!") }


        val adoption = Adoption(pet = pet, tutor = tutor)
        adoptionsRepository.save(adoption)

        pet.adopt()

        return adoption.toDTO()
    }

}
