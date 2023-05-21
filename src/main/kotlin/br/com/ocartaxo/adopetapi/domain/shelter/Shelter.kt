package br.com.ocartaxo.adopetapi.domain.shelter

import br.com.ocartaxo.adopetapi.domain.location.Location
import br.com.ocartaxo.adopetapi.domain.user.User
import jakarta.persistence.*
import lombok.NoArgsConstructor
import java.time.LocalDateTime

@Entity
@Table(name = "abrigos")
@NoArgsConstructor
data class Shelter(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,

    var phone: String,

    @Embedded
    var location: Location,
    val createdOn: LocalDateTime = LocalDateTime.now(),

    @OneToOne
    val user: User

)