package ecare.resource;

import ecare.dto.ApiError;
import ecare.dto.TariffDto;
import ecare.model.ServiceException;
import ecare.service.TariffService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotBlank;
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
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = {@Content(mediaType = "application/json",
            array = @ArraySchema(uniqueItems = true, schema = @Schema(implementation = TariffDto.class)))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
    @GetMapping("/all")
    public List<TariffDto> getAllTariffs() {
        LOGGER.info("GET all tariffs");
        return tariffService.getAllTariffs();
    }

    @Operation(summary = "Return tariffs by name of the tariff")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Found the tariff",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TariffDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "403", description = "Forbidden",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "500", description = "Internal Server Error",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "503", description = "Service Unavailable",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))}),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ApiError.class))})})
    @Parameters(value = {@Parameter(name = "tariffName", in = ParameterIn.PATH,
            description = "Name of the tariff", schema = @Schema(implementation = String.class),
            examples = {@ExampleObject(name = "Return tariff for tariffname = M",
            summary = "Return tariff for tariffname equal to M", description = "Return tariff for tariffname equal to M", value = "M")})})
    @GetMapping("{tariffName}")
    public TariffDto getTariffByName(@PathVariable("tariffName") @NotBlank String tariffName) throws ServiceException {
        LOGGER.infof("GET tariff by name %s", tariffName);
        return tariffService.findByTariffName(tariffName);
    }

}
