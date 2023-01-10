package ecare.resource;


import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.servers.Server;


import javax.ws.rs.core.Application;

/**
 * E-Care application class to apply openapi definitions
 */
@OpenAPIDefinition(info = @Info(title = "E-Care application", version = "0.1.0", description = "E-Care API", contact = @Contact(url = "https://stub?pageId=945621888", name = "Ekaterina Mokeeva")), servers = {@Server(url = "http://localhost:8383", description = "local server for development")}, security = {@SecurityRequirement(name = "accessToken")})
public class EcareApplication extends Application {
}
