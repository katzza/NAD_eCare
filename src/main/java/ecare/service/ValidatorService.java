package ecare.service;

import ecare.model.Contract;
import ecare.model.Option;
import ecare.model.ServiceException;
import org.jboss.logging.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static org.jboss.logging.Logger.getLogger;


@Service
public class ValidatorService {

    private static final Logger LOGGER = getLogger(ValidatorService.class);

    public void validateCompatibility(Contract contract, Option option) throws ServiceException {
        LOGGER.infof("Validate compatibility contract %s and option %s", contract.getBusinessId(), option.getOptionName());
        if (!contract.getTariff().getOptions().contains(option)) {
            throw new ServiceException("Bad request: This option is not available in this tariff", HttpStatus.BAD_REQUEST);
        }
    }


    public void validateRemovability(Contract contract, Option option) throws ServiceException {
        LOGGER.infof("Validate if option %s could be removed from the contract %s", option.getOptionName(), contract.getBusinessId());
        if (!contract.getOptions().contains(option)) {
            throw new ServiceException("Bad request: the option is not in the contract and cannot be removed", HttpStatus.BAD_REQUEST);
        }
    }
}
