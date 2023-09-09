package br.com.ocartaxo.kopet.api.domain.adoption

import br.com.ocartaxo.api.domain.pet.PetsRepository
import br.com.ocartaxo.api.domain.tutor.TutorsRepository
import org.springframework.stereotype.Service

@Service
class AdoptionService(
    private val adoptionsRepository: br.com.ocartaxo.kopet.api.domain.adoption.AdoptionsRepository,
    private val petsRepository: PetsRepository,
    private val tutorsRepository: TutorsRepository
) {
    fun adopt(request: br.com.ocartaxo.kopet.api.domain.adoption.AdoptionRequest): br.com.ocartaxo.kopet.api.domain.adoption.AdoptionResponse {
        val pet = petsRepository.findById(request.petId)
            .orElseThrow { IllegalArgumentException("Pet não encontrado!") }

        if (pet.isAdopted()) {
            throw java.lang.IllegalArgumentException("Pet já adotado!")
        }

        val tutor = tutorsRepository.findById(request.tutorId)
            .orElseThrow { IllegalArgumentException("Tutor não encontrado!") }


        val adoption = br.com.ocartaxo.kopet.api.domain.adoption.Adoption(pet = pet, tutor = tutor)
        adoptionsRepository.save(adoption)

        pet.adopt()

        return adoption.toDTO()
    }

    fun delete(id: Int) = adoptionsRepository.deleteById(id)

}
