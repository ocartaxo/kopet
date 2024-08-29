package br.com.ocartaxo.kopet.api.domain.shelter

import br.com.ocartaxo.kopet.api.domain.location.Location
import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "abrigo")
data class Shelter(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var email: String,
    var phone: String,

    @Embedded
    var location: Location,

    var enabled: Boolean = true,
    val createdOn: LocalDateTime = LocalDateTime.now()

)