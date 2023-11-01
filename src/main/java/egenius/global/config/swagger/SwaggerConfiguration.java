package egenius.global.config.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @io.swagger.v3.oas.annotations.info.Info(
                title = "GentleDog Swagger API",
                version = "v1",
                description = "젠틀독 Swagger API 문서 입니다."
        )
)

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customizeOpenAPI() {
        return new OpenAPI()
                // addSecurityItem란 security를 적용할 api를 설정하는 것이다.
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                // components란 security에 필요한 정보를 설정하는 것이다.
                .components(new Components()
                        // addSecuritySchemes란 security에 필요한 정보를 설정하는 것이다.
                        .addSecuritySchemes("bearerAuth",
                                new SecurityScheme()
                                        .name("bearerAuth")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));

    }
}