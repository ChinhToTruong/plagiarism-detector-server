package zev.plagiarismdetectorserver.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.List;


@Configuration
@Profile("!prod")
public class SwaggerConfig {

  @Bean
  public OpenAPI openAPI(@Value("${openapi.service.title}") String title,
      @Value("${openapi.service.version}") String version,
      @Value("${openapi.service.server}") String serverUrl) {

    return new OpenAPI()
        .servers(List.of(new Server().url(serverUrl)))
        .info(new Info().title(title)
            .description("Plagiarism detector server documents")
            .version(version)
            .license(new License().name("Apache 2.0").url("https://springdoc.org")));
  }

  @Bean
  public GroupedOpenApi groupedOpenApi(@Value("${openapi.service.api-docs}") String apiDocs) {
    return GroupedOpenApi.builder()
        .group(apiDocs)
        .packagesToScan("zev.plagiarismdetectorserver.controller")
        .build();
  }

}
