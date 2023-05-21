package br.com.ocartaxo.adopetapi.domain.token

import br.com.ocartaxo.adopetapi.domain.user.User
import jakarta.persistence.*

@Entity
@Table(name = "tokens")
data class Token(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "token_value")
    val tokenValue: String,
    @Column(name = "token_type")
    @Enumerated(value = EnumType.STRING)
    val tokenType: TokenType? = TokenType.BEARER,
    var revoked: Boolean = false,
    var expired: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) {
    fun isValid(): Boolean = !revoked && !expired
}
