package ecare.resource;

import ecare.dto.TariffDto;
import ecare.model.ServiceException;
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
@RequestMapping(value = "ecare/v1/tariff", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
public class TariffController {

    @Autowired
    TariffService tariffService;

    private static final Logger LOGGER = getLogger(TariffController.class);

    @GetMapping("/all")
    public List<TariffDto> getAllTariffs() {
        LOGGER.info("GET all tariffs");
        return tariffService.getAllTariffs();
    }

    @GetMapping("{tariffName}")
    public TariffDto getTariffByName(@PathVariable("tariffName") @NotBlank String tariffName) throws ServiceException {
        LOGGER.infof("GET tariff by name %s", tariffName);
        return tariffService.findByTariffName(tariffName);
    }

}
