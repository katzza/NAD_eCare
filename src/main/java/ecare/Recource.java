package ecare;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.dto.UserDto;
import ecare.model.Contract;
import ecare.model.ServiceException;
import ecare.service.ContractService;
import ecare.service.TariffService;
import ecare.service.UserService;
import org.jboss.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @Autowired
    UserService userService;

    private static final Logger LOGGER = getLogger(Recource.class);

    @GetMapping("/tariffs")
    public List<TariffDto> getAllTariffs() {
        LOGGER.info("GET all tariffs");
        return tariffService.getAllTariffs();
    }

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

    @GetMapping("/tariff/{tariffName}")
    public TariffDto getTariffByName(@PathVariable("tariffName") @NotBlank String tariffName) throws ServiceException {
        LOGGER.infof("GET tariff by name %s", tariffName);
        return tariffService.findByTariffName(tariffName);
    }

    @GetMapping("/contracts")
    public List<ContractDto> getAllContracts() {
        LOGGER.info("GET all contracts");
        return contractService.getAllContracts();
    }


    @PostMapping("/newuser")
    public UserDto newUser(@Valid @RequestBody UserDto userDto) throws ServiceException {
        LOGGER.info("Create new user");
        if (userDto == null) {
            throw new ServiceException("User is not present", HttpStatus.BAD_REQUEST);
        }
        return userService.createUser(userDto);
    }
}
