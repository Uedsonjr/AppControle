package br.com.uedsonjr.AppControle.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class Config {

  @Bean 
  public OpenAPI customOpenAPI() {
      return new OpenAPI()
                .components(
                   new Components().addSecuritySchemes("basicScheme",
                       new SecurityScheme().type(SecurityScheme.Type.HTTP)
                           .scheme("basic")))
                .info(
                   new Info()
                        .title(" Spring: API Rest para Controle de Contatos")
                        .description("Gerenciamento de sistema de cadastro;" + "Controle de Pessoas e Contatos.")
                        .contact(new Contact()
                           .name("Uedson Junior")
                           .email("Uedsonjr159@gmail.com")
                           .url("http://localhost:8020"))
                               .version("Versão 0.0.1-SNAPSHOT"));

    }
}