package ecare.service;

import ecare.model.Contract;
import ecare.model.Option;
import ecare.model.ServiceException;
import ecare.model.Tariff;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@Service
public class ValidatorService {

    public void validateCompatibility(Contract contract, Option option) throws ServiceException {
        if (!contract.getTariff().getOptions().contains(option)) {
            throw new ServiceException("Bad request: This option is not available in this tariff", HttpStatus.BAD_REQUEST);
        }
    }

    public void validateRemovability(Contract contract, Option option) throws ServiceException {
        if (!contract.getOptions().contains(option)) {
            throw new ServiceException("Bad request: the option is not in the contract and cannot be removed", HttpStatus.BAD_REQUEST);
        }
    }
}
