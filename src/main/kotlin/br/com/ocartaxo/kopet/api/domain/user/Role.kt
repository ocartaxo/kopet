package br.com.ocartaxo.kopet.api.domain.user

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

enum class Role(private val permissions: Set<Permission>) {
    TUTOR(
        setOf(
            Permission.TUTOR_CREATE,
            Permission.TUTOR_READ,
            Permission.TUTOR_UPDATE,
            Permission.TUTOR_DELETE,
            Permission.ADOPTION_CREATE
        )
    ),
    SHELTER(setOf(
        Permission.SHELTER_CREATE,
        Permission.SHELTER_READ,
        Permission.SHELTER_UPDATE,
        Permission.SHELTER_DELETE,
        Permission.ADOPTION_DELETE,
        Permission.PET_CREATE,
        Permission.PET_UPDATE,
        Permission.PET_DELETE
    ));

    fun authorities(): List<SimpleGrantedAuthority> {
        val authorities = permissions.stream()
            .map { p -> SimpleGrantedAuthority(p.permission) }.collect(Collectors.toList())

        authorities.add(SimpleGrantedAuthority("ROLE_${this.name}"))

        return authorities
    }
}