package ecare.resource;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.model.ServiceException;
import ecare.service.ContractService;
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

    @GetMapping("/possibletariffs")
    public List<TariffDto> getPossibleTariffs(@RequestParam @NotBlank String contractId) throws ServiceException {
        LOGGER.infof("GET tariffs possible for contract %s", contractId);
        return contractService.getTariffsToContract(contractId);
    }

    @PutMapping("/changetariff")
    @ResponseBody
    public Contract changeTariff(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String newTariffName) throws ServiceException {
        LOGGER.infof("Select new tariff %s to contract %s", newTariffName, contractId);
        return contractService.setTariffToContract(contractId, newTariffName);
    }

    @PutMapping("/addoption")
    @ResponseBody
    public Contract addOption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) throws ServiceException {
        LOGGER.infof("Add option %s to contract %s", optionName, contractId);
        return contractService.addOption(contractId, optionName);
    }

    @PutMapping("/removeoption")
    @ResponseBody
    public Contract removeOption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) throws ServiceException {
        LOGGER.infof("Remove option %s from contract %s", optionName, contractId);
        return contractService.removeOption(contractId, optionName);
    }

    @GetMapping("/all")
    public List<ContractDto> getAllContracts() {
        LOGGER.info("GET all contracts");
        return contractService.getAllContracts();
    }

}
