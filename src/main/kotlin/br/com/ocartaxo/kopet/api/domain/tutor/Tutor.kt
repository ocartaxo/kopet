package br.com.ocartaxo.kopet.api.domain.tutor

import br.com.ocartaxo.kopet.api.domain.location.Location
import br.com.ocartaxo.kopet.api.domain.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class Tutor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @OneToOne
    val user: User,

    var name: String,

    val createdOn: LocalDateTime = LocalDateTime.now(),
    var updateOn: LocalDateTime = LocalDateTime.now(),

    var phone: String? = null,
    var about: String? = null,
    var image: String? = null,

    @Embedded
    var location: Location? = null
)
