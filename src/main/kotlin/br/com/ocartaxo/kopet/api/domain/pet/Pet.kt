package br.com.ocartaxo.kopet.api.domain.pet


import br.com.ocartaxo.api.domain.shelter.Shelter
import jakarta.persistence.*
import lombok.NoArgsConstructor

@Entity
@NoArgsConstructor
data class Pet(
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int? = null,
    var name: String,
    var age: String,
    var description: String,
    var image: String,
    @ManyToOne
    @JoinColumn(name = "abrigo_id")
    var shelter: Shelter,
    var adopted: Boolean = false,
    @Enumerated(EnumType.STRING)
    var specie: PetSpecie,
    @Enumerated(EnumType.STRING)
    var size: PetSize? = null,


    )