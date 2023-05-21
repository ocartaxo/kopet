package br.com.ocartaxo.adopetapi.domain.tutor

import br.com.ocartaxo.adopetapi.domain.location.Location
import br.com.ocartaxo.adopetapi.domain.user.User
import jakarta.persistence.*
import lombok.NoArgsConstructor
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.time.LocalDateTime

@Entity
@Table(name="tutores")
@NoArgsConstructor
data class Tutor(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,

    var createdOn: LocalDateTime = LocalDateTime.now(),

    var phone: String? = null,
    var about: String? = null,
    var image: String? = null,

    @Embedded
    var location: Location? = null,
    @OneToOne
    val user: User
)
