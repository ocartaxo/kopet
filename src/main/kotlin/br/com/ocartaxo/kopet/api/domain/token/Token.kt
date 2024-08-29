package br.com.ocartaxo.kopet.api.domain.token

import br.com.ocartaxo.kopet.api.domain.user.User
import jakarta.persistence.*

@Entity
data class Token(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    val tokenValue: String,
    @Enumerated(value = EnumType.STRING)
    val tokenType: TokenType = TokenType.BEARER,
    var revoked: Boolean = false,
    var expired: Boolean = false,
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    val user: User
) {
    fun isValid(): Boolean = !revoked && !expired
}
