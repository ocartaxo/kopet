package br.com.ocartaxo.kopet.api.domain.tutor

import br.com.ocartaxo.kopet.api.domain.user.RegistrationService
import jakarta.transaction.Transactional
import org.slf4j.LoggerFactory
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TutorService(
    private val tutorsRepository: TutorsRepository,
    private val registrationService: RegistrationService
) {

    private val logger = LoggerFactory.getLogger(TutorService::class.java)

    @Transactional
    fun register(tutorRequest: TutorRequest): TutorResponse {
        logger.info("Cadastrando o usuário `${tutorRequest.email}`")

        val t = tutorRequest.toEntity(registrationService.register(tutorRequest.toRegisterRequest()))

        tutorsRepository.save(t)
        return t.toDTO()
    }

    @Cacheable("tutores")
    fun list(pageable: Pageable): Page<TutorSummaryResponse> =
        tutorsRepository.findAll(pageable).map(Tutor::toSummaryResponse)

    @Cacheable("tutor_por_id")
    fun show(id: Int): TutorResponse = tutorsRepository.findById(id)
        .orElseThrow { IllegalArgumentException("Tutor de id $id não está cadastrado na base!") }.toDTO()

    @Transactional
    fun update(request: TutorUpdateRequest): TutorResponse {
        val t: Tutor = tutorsRepository.findById(request.id).orElseThrow()
        t.update(request)
        return t.toDTO()
    }

    fun delete(id: Int) = tutorsRepository.deleteById(id)


}
