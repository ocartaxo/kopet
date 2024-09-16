package br.com.ocartaxo.kopet.api.domain.pet

import br.com.ocartaxo.kopet.api.domain.shelter.ShelterRepository
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class PetService(
    private val petsRepository: PetsRepository,
    private val shelterRepository: ShelterRepository
) {

    private val logger = LoggerFactory.getLogger(javaClass)

    @Transactional
    fun register(request: PetRequest): PetResponse {
        val shelter = shelterRepository.findById(request.shelterId).orElseThrow { RuntimeException() }
        val pet = request.toEntity(shelter)

        return petsRepository.save(pet).toDTO()
    }

    @Cacheable("pets")
    fun list(pageable: Pageable) = petsRepository.findAllByAdoptedIsFalse(pageable).map(Pet::toSummaryDTO)

    @Cacheable("pete_por_id")
    fun show(id: Int) = petsRepository.findById(id).orElseThrow { RuntimeException("Pet de id '$id' não encontrado!") }.toDTO()

    @Transactional
    fun update(request: PetUpdateRequest): PetResponse {
        val pet = petsRepository.findById(request.id)
            .orElseThrow { RuntimeException("Pet não encontrado!") }

        val shelter = shelterRepository.findByIdOrNull(request.shelterId)

        pet.update(request, shelter)

        return pet.toDTO()

    }
    @Transactional
    fun delete(id: Int) = petsRepository.deleteById(id)


}