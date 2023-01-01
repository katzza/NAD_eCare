package ecare;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.service.ContractService;
import ecare.service.TariffService;
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
@RequestMapping(produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class Recource {

    @Autowired
    TariffService tariffService;

    @Autowired
    ContractService contractService;

    private static final Logger LOGGER = getLogger(Recource.class);

    @GetMapping("/tariffs")
    public List<TariffDto> getAllTariffs() {
        LOGGER.info("GET all tariffs");
        return tariffService.getAllTariffs();
    }

    @GetMapping("/possibletariffs")
    public List<TariffDto> getPossibleTariffs(@RequestParam @NotBlank String contractId) {
        LOGGER.info("GET tariffs possible for change ");
        return contractService.getPossibleTariffs(contractId);
    }

    @PostMapping("/addoption")
    @ResponseBody
    public Contract addoption(@RequestParam @NotBlank String contractId, @RequestParam @NotBlank String optionName) {
        LOGGER.info("Add option to contract");
        return contractService.addOption(contractId, optionName);
    }

    @GetMapping("/tariff/{tariffName}")
    public TariffDto getTariffByName(@PathVariable("tariffName") @NotBlank String tariffName) {
        LOGGER.info("GET tariff by name");
        return tariffService.findByTariffName(tariffName);
    }

    @GetMapping("/contracts")
    public List<ContractDto> getAllContracts() {
        LOGGER.info("GET all contracts");
        return contractService.getAllContracts();
    }

}
