package ecare.resource;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

/**
 * E-Care application class to apply openapi definitions
 */
@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(title = "E-Care application",
        version = "0.1.0",
        description = "E-Care API",
        contact = @Contact(url = "https://stub?pageId=945621888",
                name = "Ekaterina Mokeeva")),
        servers = {@Server(url = "http://localhost:8383",
                description = "local server for development")})
public class EcareApplication extends Application {
}
