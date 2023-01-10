package ecare.resource;

import ecare.dto.TariffDto;
import ecare.model.ServiceException;
import ecare.service.TariffService;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.enums.ParameterIn;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameter;
import org.eclipse.microprofile.openapi.annotations.parameters.Parameters;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.media.ExampleObject;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;

import static org.jboss.logging.Logger.getLogger;

@Validated
@CrossOrigin
@RestController
@RequestMapping(value = "ecare/v1/tariff", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class TariffController {

    @Autowired
    TariffService tariffService;

    private static final Logger LOGGER = getLogger(TariffController.class);

    @Operation(summary = "Return all tariffs")
    @APIResponses(value = {@APIResponse(responseCode = "400", description = "Bad Request"),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "404", description = "Not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error"),
            @APIResponse(responseCode = "503", description = "Service Unavailable"),
            @APIResponse(responseCode = "504", description = "Gateway Timeout"),
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema =
            @Schema(implementation =
                    ArrayList.class)))})
    @GetMapping("/all")
    public List<TariffDto> getAllTariffs() {
        LOGGER.info("GET all tariffs");
        return tariffService.getAllTariffs();
    }

    @Operation(summary = "Return tariffs by name of the tariff")
    @Parameters(value = {@Parameter(name = "tariffname", in = ParameterIn.PATH, description =
            "Name of the tariff", schema = @Schema(implementation = String.class), examples = {
            @ExampleObject(name = "Return tariff for tariffname = M", summary =
                    "Return tariff for tariffname equal to M", description = "Return tariff for tariffname equal to M",
                    value = "M")})})
    @APIResponses(value = {@APIResponse(responseCode = "400", description = "Bad Request"),
            @APIResponse(responseCode = "401", description = "Unauthorized"),
            @APIResponse(responseCode = "403", description = "Forbidden"),
            @APIResponse(responseCode = "404", description = "Not found"),
            @APIResponse(responseCode = "500", description = "Internal Server Error"),
            @APIResponse(responseCode = "503", description = "Service Unavailable"),
            @APIResponse(responseCode = "504", description = "Gateway Timeout"),
            @APIResponse(responseCode = "200", description = "OK", content = @Content(schema =
            @Schema(implementation =
                    TariffDto.class)))})
    @GetMapping("{tariffName}")
    public TariffDto getTariffByName(@PathVariable("tariffName") @NotBlank String tariffName) throws ServiceException {
        LOGGER.infof("GET tariff by name %s", tariffName);
        return tariffService.findByTariffName(tariffName);
    }

}
