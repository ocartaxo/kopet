package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.ocartaxo.kopet.api.domain.location.Location
import br.com.ocartaxo.kopet.api.domain.user.User
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "abrigo")
data class Shelter(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    @OneToOne
    val user: User,
    var phone: String,

    @Embedded
    var location: Location,

    var enabled: Boolean = true,
    val createdOn: LocalDateTime = LocalDateTime.now(),
    var updatedOn: LocalDateTime = LocalDateTime.now()

)