package br.com.ocartaxo.adopetapi.domain.pet


import br.com.ocartaxo.adopetapi.domain.shelter.Shelter
import jakarta.persistence.*
import lombok.NoArgsConstructor

@Entity
@Table(name="pets")
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