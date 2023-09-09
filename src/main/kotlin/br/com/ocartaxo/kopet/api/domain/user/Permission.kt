package br.com.ocartaxo.kopet.api.domain.user

enum class Permission(val permission: String) {

    // Tutores
    TUTOR_CREATE("tutor:registrar"),
    TUTOR_READ("tutor:exibir"),
    TUTOR_UPDATE("tutor:atualizar"),
    TUTOR_DELETE("tutor:deletar"),
    ADOPTION_CREATE("adocao:registrar"),

    // Abrigos
    SHELTER_CREATE("abrigo:registrar"),
    SHELTER_READ("abrigo:exibir"),
    SHELTER_UPDATE("abrigo:atualizar"),
    SHELTER_DELETE("abrigo:deletar"),
    ADOPTION_DELETE("adocao:deletar"),
    PET_CREATE("pet:registrar"),
    PET_UPDATE("pet:atualizar"),
    PET_DELETE("pet:deletar")

}