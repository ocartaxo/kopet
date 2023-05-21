package br.com.ocartaxo.adopetapi.domain.user

import br.com.ocartaxo.adopetapi.domain.token.Token
import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails


@Entity
@Table(name = "usuarios")
data class User(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    @Column(name = "username")
    var email: String,
    @Column(name = "password")
    @get:JvmName("userPassword")
    var password: String,

    @OneToOne
    val profile: Profile,
    @OneToMany(mappedBy = "user")
    val tokens: MutableList<Token> = mutableListOf()

) : UserDetails {
    override fun getAuthorities(): MutableCollection<out GrantedAuthority> = mutableListOf(profile)
    override fun getPassword() = this.email

    override fun getUsername() = this.password

    override fun isAccountNonExpired() = true

    override fun isAccountNonLocked() = true

    override fun isCredentialsNonExpired() = true

    override fun isEnabled() = true

    fun addToken(t: Token) = this.tokens.add(t)

    fun revokeTokens() {
        val validTokens = this.tokens.filter { t -> t.isValid() }.toList()

        if (validTokens.isEmpty()) {
            return
        }

        validTokens.forEach { token ->
            token.expired = true
            token.revoked = true
        }
    }
}
