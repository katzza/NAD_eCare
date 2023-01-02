package ecare.service;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.model.Option;
import ecare.model.ServiceException;
import ecare.model.Tariff;
import ecare.repository.ContractRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    OptionService optionService;

    @Autowired
    TariffService tariffService;

    @Autowired
    ValidatorService validatorService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ContractDto> getAllContracts() {
        List<Contract> contractEntities = contractRepository.findAll();
        return contractEntities.stream().map(this::convertToDto).toList();
    }

    public Contract findEntityByBusinessId(String contractBusinessId) throws ServiceException {
        return contractRepository.findByBusinessId(contractBusinessId).orElseThrow(() -> new ServiceException("Object not found: Contract ID- " + contractBusinessId, HttpStatus.NOT_FOUND));
    }

    public ContractDto findByBusinessId(String contractBusinessId) throws ServiceException {
        Contract contract = findEntityByBusinessId(contractBusinessId);
        return convertToDto(contract);
    }

    /**
     * By tariff-change all options from the previous tariff will be deleted
     *
     * @param contractId id of contract to be changed
     * @param tariffName id of new tariff to the contract
     * @return updated contract with the new tariff
     */
    public Contract setTariffToContract(String contractId, String tariffName) throws ServiceException {
        Contract contract = findEntityByBusinessId(contractId);
        Tariff tariff = tariffService.getEntityByName(tariffName);
        contract.setTariff(tariff);
        contract.setOptions(new HashSet<>());
        return contractRepository.save(contract);
    }

    public Contract createContract(ContractDto contract) {
        Contract newContract = convertToEntity(contract);
        return saveContract(newContract);
    }

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract addOption(String contractId, String optionName) throws Exception {
        Contract contract = findEntityByBusinessId(contractId);
        Option option = optionService.findEntityByOptionName(optionName);
        validatorService.validateCompatibility(contract, option);
        contract.getOptions().add(option);
        return contractRepository.save(contract);
    }

    public Contract removeOption(String contractId, String optionName) throws ServiceException {
        Contract contract = findEntityByBusinessId(contractId);
        Option option = optionService.findEntityByOptionName(optionName);
        validatorService.validateRemovability(contract, option);
        contract.getOptions().remove(option);
        return contractRepository.save(contract);
    }

    public List<TariffDto> getPossibleTariffs(String contractId) throws ServiceException {
        int currentTariffGrade = findEntityByBusinessId(contractId).getTariff().getTariffGrade();
        return tariffService.getPossibleTariffs(currentTariffGrade);
    }

    public ContractDto convertToDto(Contract contractEntity) {
        return modelMapper.map(contractEntity, ContractDto.class);
    }

    public Contract convertToEntity(ContractDto contractDto) {
        return modelMapper.map(contractDto, Contract.class);
    }
}
