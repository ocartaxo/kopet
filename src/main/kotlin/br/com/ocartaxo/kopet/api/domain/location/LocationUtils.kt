package br.com.ocartaxo.kopet.api.domain.location

fun Location.toDTO() = LocationDTO(
    address=this.address,
    number=this.number,
    district=this.district,
    city=this.city,
    state=this.state,
    zipCode=this.zipCode
)

fun LocationDTO.toEntity() = Location(
    address=this.address,
    number=this.number,
    district=this.district,
    city=this.city,
    state=this.state,
    zipCode=this.zipCode
)