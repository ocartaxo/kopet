package br.com.ocartaxo.adopetapi.domain.user

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name="perfis")
data class Profile(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Enumerated(EnumType.STRING)
    val type: ProfileType
): GrantedAuthority{
    override fun getAuthority() = type.name
}
