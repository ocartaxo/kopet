package br.com.ocartaxo.kopet.api.infra.springdoc

import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Contact
import io.swagger.v3.oas.models.info.Info
import io.swagger.v3.oas.models.info.License
import io.swagger.v3.oas.models.security.SecurityScheme
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringDocConfigurations {

    @Bean
    fun customOpenAPI(): OpenAPI {
        return OpenAPI()
            .components(
                Components()
                    .addSecuritySchemes(
                        "bearer-key",
                        SecurityScheme().type(SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")
                    )
            )
            .info(
                Info()
                    .title("Adopet API")
                    .description(
                        """
                        API REST da aplicação Adopet com as funcionalidades de registrar tutores, abrigos, pets e novas adoçãoes 
                    """.trimIndent()
                    )
                    .contact(
                        Contact()
                            .name("Otávio Cartaxo")
                            .email("oacartaxo@gmail.com")
                    )
                    .license(
                        License()
                            .name("MIT License")
                            .url("https://adopet.org/licenca")
                    )
            )



    }

}