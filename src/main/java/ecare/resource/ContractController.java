package ecare.resource;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.model.ServiceException;
import ecare.service.ContractService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
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
import java.util.ArrayList;
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

    @Operation(summary = "Return possible tariffs by business contract Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Founded possible tariffs",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TariffDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout")})
    @Parameters(value = {@Parameter(name = "contractId", in = ParameterIn.QUERY,
            description = "Business Id of the contract", schema = @Schema(implementation = String.class),
            examples = {@ExampleObject(name = "Return tariffs possible for the contract ID '321'",
            summary = "Return tariffs possible for the contract ID equal to '321'",
            description = "Return tariffs possible for the contract ID equal to", value = "321")})})
    @GetMapping("/possibletariffs")
    public List<TariffDto> getPossibleTariffs(@RequestParam @NotBlank String contractId) throws ServiceException {
        LOGGER.infof("GET tariffs possible for contract %s", contractId);
        return contractService.getTariffsToContract(contractId);
    }

    @Operation(summary = "Change tariff by business contract Id")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Change tariff by business contract Id",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = TariffDto.class))}),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout")})
    @Parameters(value = {
            @Parameter(name = "contractId", in = ParameterIn.QUERY,
            description = "Business Id of the contract", schema = @Schema(implementation = String.class)),
            @Parameter(name = "newTariffName", in = ParameterIn.QUERY,
            description = "New tariff for the contract", schema = @Schema(implementation = String.class))
    })
    @PutMapping("/changetariff")
    @ResponseBody
    public ContractDto changeTariff(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String newTariffName) throws ServiceException {
        LOGGER.infof("Select new tariff %s to contract %s", newTariffName, contractId);
        return contractService.setTariffToContract(contractId, newTariffName);
    }

    @PutMapping("/addoption")
    @ResponseBody
    public ContractDto addOption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) throws ServiceException {
        LOGGER.infof("Add option %s to contract %s", optionName, contractId);
        return contractService.addOption(contractId, optionName);
    }

    @PutMapping("/removeoption")
    @ResponseBody
    public ContractDto removeOption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) throws ServiceException {
        LOGGER.infof("Remove option %s from contract %s", optionName, contractId);
        return contractService.removeOption(contractId, optionName);
    }

    @Operation(summary = "Return all contracts")
    @ApiResponses(value = {@ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "403", description = "Forbidden"),
            @ApiResponse(responseCode = "404", description = "Not found"),
            @ApiResponse(responseCode = "500", description = "Internal Server Error"),
            @ApiResponse(responseCode = "503", description = "Service Unavailable"),
            @ApiResponse(responseCode = "504", description = "Gateway Timeout"),
            @ApiResponse(responseCode = "200", description = "OK", content = @Content(schema =
            @Schema(implementation =
                    ArrayList.class)))})
    @GetMapping("/all")
    public List<ContractDto> getAllContracts() {
        LOGGER.info("GET all contracts");
        return contractService.getAllContracts();
    }

}
