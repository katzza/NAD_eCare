package ecare;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.service.ContractService;
import ecare.service.TariffService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.jboss.logging.Logger.getLogger;

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

    @PostMapping("/addoption")
    public Contract addoption(Long contractId, Long optionId) {
        LOGGER.info("Add option to contract");
        return contractService.addOption(contractId, optionId);
    }

    @GetMapping("{id}")
    public TariffDto getById(@PathVariable("id") Long id) throws Exception {
        LOGGER.info("GET tariff by id");
        return tariffService.findById(id);
    }

    @GetMapping("/contracts")
    public List<ContractDto> getAllContracts() {
        LOGGER.info("GET all contracts");
        return contractService.getAllContracts();
    }


}
