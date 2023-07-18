package br.com.ocartaxo.adopetapi.domain.tutor

import br.com.ocartaxo.adopetapi.infra.security.JwtService
import org.slf4j.LoggerFactory
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class TutorService(
    private val tutorsRepository: TutorsRepository,
) {

    private val logger = LoggerFactory.getLogger(TutorService::class.java)

    fun register(request: TutorRequest): TutorResponse {
        logger.info("Cadastrando o usuário `${request.email}`")
        val t: Tutor = request.toEntity()
        tutorsRepository.save(t)
        return t.toDTO()
    }

    fun list(pageable: Pageable): Page<TutorSummaryResponse> =
        tutorsRepository.findAll(pageable).map(Tutor::toSummaryResponse)

    fun show(id: Int): TutorResponse = tutorsRepository.findById(id)
        .orElseThrow { IllegalArgumentException("Tutor de id $id não está cadastrado na base!") }.toDTO()

    fun update(request: TutorUpdateRequest): TutorResponse {
        val t: Tutor = tutorsRepository.findById(request.id).orElseThrow()
        t.update(request)
        return t.toDTO()
    }

    fun delete(id: Int) = tutorsRepository.deleteById(id)


}
