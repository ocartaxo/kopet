package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.location.Location
import jakarta.persistence.*
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "abrigo")
@NoArgsConstructor
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