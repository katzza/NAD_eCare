package ecare.resource;

import ecare.dto.ApiError;
import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.ServiceException;
import ecare.service.ContractService;
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
@RequestMapping(value = "ecare/v1/contract", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class ContractController {

    @Autowired
    ContractService contractService;

    private static final Logger LOGGER = getLogger(ContractController.class);

    @Operation(summary = "Possible tariffs by business contract Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Founded possible tariffs",
            content = {@Content(mediaType = "application/json",
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
    @Parameters(value = {@Parameter(name = "contractId", in = ParameterIn.QUERY,
            description = "Business Id of the contract", schema = @Schema(implementation = String.class),
            examples = {@ExampleObject(name = "Return tariffs possible for the contract ID 321",
            summary = "Return tariffs possible for the contract ID equal to 321",
            description = "Return tariffs possible for the contract ID equal to 321", value = "321")})})
    @GetMapping("/possibletariffs")
    public List<TariffDto> getPossibleTariffs(@RequestParam @NotBlank String contractId) throws ServiceException {
        LOGGER.infof("GET tariffs possible for contract %s", contractId);
        return contractService.getTariffsToContract(contractId);
    }

    @Operation(summary = "Change tariff by business contract ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Change tariff by business contract ID",
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
    @Parameters(value = {
            @Parameter(name = "contractId", in = ParameterIn.QUERY,
            description = "Business ID of the contract", schema = @Schema(implementation = String.class)),
            @Parameter(name = "newTariffName", in = ParameterIn.QUERY,
            description = "New tariff for the contract", schema = @Schema(implementation = String.class))})
    @PutMapping("/changetariff")
    @ResponseBody
    public ContractDto changeTariff(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String newTariffName) throws ServiceException {
        LOGGER.infof("Select new tariff %s to contract %s", newTariffName, contractId);
        return contractService.setTariffToContract(contractId, newTariffName);
    }

    @Operation(summary = "Add option to contract by business contract ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Add option to contract by business contract ID",
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
    @Parameters(value = {
            @Parameter(name = "contractId", in = ParameterIn.QUERY,
                    description = "Business ID of the contract", schema = @Schema(implementation = String.class)),
            @Parameter(name = "optionName", in = ParameterIn.QUERY,
                    description = "New option for the contract", schema = @Schema(implementation = String.class))})
    @PutMapping("/addoption")
    @ResponseBody
    public ContractDto addOption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) throws ServiceException {
        LOGGER.infof("Add option %s to contract %s", optionName, contractId);
        return contractService.addOption(contractId, optionName);
    }

    @Operation(summary = "Remove option to contract by business contract ID")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Remove option to contract by business contract ID",
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
    @Parameters(value = {
            @Parameter(name = "contractId", in = ParameterIn.QUERY,
                    description = "Business ID of the contract", schema = @Schema(implementation = String.class)),
            @Parameter(name = "optionName", in = ParameterIn.QUERY,
                    description = "Option to be removed", schema = @Schema(implementation = String.class))})
    @PutMapping("/removeoption")
    @ResponseBody
    public ContractDto removeOption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) throws ServiceException {
        LOGGER.infof("Remove option %s from contract %s", optionName, contractId);
        return contractService.removeOption(contractId, optionName);
    }

    @Operation(summary = "All contracts")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "OK", content = @Content(
            mediaType = "application/json", array = @ArraySchema(uniqueItems = true, schema = @Schema(implementation = ContractDto.class)))),
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
    public List<ContractDto> getAllContracts() {
        LOGGER.info("GET all contracts");
        return contractService.getAllContracts();
    }

}
