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

    @Column(name="profile")
    @Enumerated(value = EnumType.STRING)
    val role: Role,
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY, cascade = [CascadeType.REMOVE])
    val tokens: MutableList<Token> = mutableListOf()

) : UserDetails {
    override fun getAuthorities(): Collection<GrantedAuthority> = role.authorities()
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
