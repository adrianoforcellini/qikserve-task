package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI myOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("exemplo")
                        .version("0.0.1-SNAPSHOT")
                        .contact(new Contact().name("Bradesco Seguros"))
                        .description("Microserviço do Bradesco seguros"));
    }
}
