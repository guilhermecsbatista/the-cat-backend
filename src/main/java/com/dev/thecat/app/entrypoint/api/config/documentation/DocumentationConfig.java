package com.dev.thecat.app.entrypoint.api.config.documentation;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DocumentationConfig {

  @Value("${application.version}")
  private String appVersion;

  @Value("${application.description}")
  private String appDescription;

  @Bean
  public OpenAPI customOpenApi() {
    return new OpenAPI()
        .addSecurityItem(
            new SecurityRequirement().addList("bearer-key", Arrays.asList("read", "write")))
        .components(
            new Components()
                .addSecuritySchemes(
                    "bearer-key",
                    new SecurityScheme()
                        .name("Authorization")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                )
        )
        .info(
            new Info()
                .title("The Cat API")
                .description(appDescription)
                .version(appVersion)
        );

  }
}
