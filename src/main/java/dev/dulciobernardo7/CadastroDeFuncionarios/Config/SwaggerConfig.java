package dev.dulciobernardo7.CadastroDeFuncionarios.Config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        Contact contact = new Contact();
        contact.name("Dulcio Bernardo");
        contact.email("dulciobernardo77@gmail.com");

        Info info = new Info();
        info.title("CadastroDeFuncionario");
        info.description("Um sistema web em Spring Boot para gerenciar pessoas e tarefas, com interface em Thymeleaf, relacionamentos entre entidades e navegação padronizada.");
        info.version("v1");
        info.contact(contact);

        return new OpenAPI().info(info);
    }

}
