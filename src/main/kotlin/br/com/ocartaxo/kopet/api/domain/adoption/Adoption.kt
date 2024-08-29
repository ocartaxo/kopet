package br.com.ocartaxo.kopet.api.domain.adoption

import br.com.ocartaxo.kopet.api.domain.pet.Pet
import br.com.ocartaxo.kopet.api.domain.tutor.Tutor
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "adocao")
data class Adoption(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val date: LocalDateTime = LocalDateTime.now(),

    @OneToOne
    @JoinColumn(name = "pet_id")
    val pet: Pet,
    @ManyToOne
    @JoinColumn(name = "tutor_id")
    val tutor: Tutor,

    )