package br.com.ocartaxo.kopet.api.domain.tutor

import br.com.ocartaxo.kopet.api.domain.location.Location
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Tutor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var email: String,
    var password: String,

    var createdOn: LocalDateTime = LocalDateTime.now(),

    var phone: String? = null,
    var about: String? = null,
    var image: String? = null,

    @Embedded
    var location: Location? = null
)
