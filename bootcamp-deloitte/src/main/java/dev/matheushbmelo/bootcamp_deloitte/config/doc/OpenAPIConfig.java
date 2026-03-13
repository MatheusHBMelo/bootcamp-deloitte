package dev.matheushbmelo.bootcamp_deloitte.config.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI openAPI() {
        Contact contact = new Contact();
        contact.setName("Matheus Barbosa");
        contact.setEmail("matheushbmelo@gmail.com");

        License mitLicense = new License();
        mitLicense.name("MIT License").url("https://choosealicense.com/licenses/mit/");

        Info info = new Info();
        info.title("CRUD de Usuário - Bootcamp Deloitte");
        info.version("1.0.0");
        info.contact(contact);
        info.description("API Rest com CRUD de usuário desenvolvida no bootcamp da Deloitte sob intrução de Renato Santiago");
        info.license(mitLicense);

        return new OpenAPI().info(info);
    }
}
