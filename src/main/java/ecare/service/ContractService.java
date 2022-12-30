package ecare.service;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.model.Option;
import ecare.model.Tariff;
import ecare.repository.ContractRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    OptionService optionService;

    @Autowired
    TariffService tariffService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ContractDto> getAllContracts() {
        List<Contract> contractEntities = contractRepository.findAll();
        return contractEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Contract findEntityByBusinessId(String contractBusinessId) {
        return contractRepository.findByBusinessId(contractBusinessId).orElseThrow(() -> new EntityNotFoundException(contractBusinessId));
    }

    public ContractDto findByBusinessId(String contractBusinessId) {
        Contract contract = contractRepository.findByBusinessId(contractBusinessId).orElseThrow(() -> new EntityNotFoundException(contractBusinessId));
        return convertToDto(contract);
    }

    /**
     * By tariff-change all options from the previous tariff will be deleted
     *
     * @param contractId id of contract to be changed
     * @param tariffName id of new tariff to the contract
     * @return updated contract with the new tariff
     * @throws Exception when entity not found
     */
    public Contract setTariffToContract(String contractId, String tariffName) throws Exception {
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

    public Contract addOption(String contractId, String optionName) {
        //todo not found + errors
        Contract contract = findEntityByBusinessId(contractId);
        Option option = optionService.findEntityByOptionName(optionName);
        contract.getOptions().add(option);
        return contractRepository.save(contract);
    }

    public Contract removeOption(String contractId, String optionName) {
        //todo not found + errors
        Contract contract = findEntityByBusinessId(contractId);
        Option option = optionService.findEntityByOptionName(optionName);
        contract.getOptions().remove(option);
        return contractRepository.save(contract);
    }

    public ContractDto convertToDto(Contract contractEntity) {
        ContractDto contract = modelMapper.map(contractEntity, ContractDto.class);
        contract.setTariff(modelMapper.map(contractEntity.getTariff(), TariffDto.class));
        return contract;
    }

    public Contract convertToEntity(ContractDto contractDto) {
        return modelMapper.map(contractDto, Contract.class);
    }

    public List<TariffDto> getPossibleTariffs(String contractId) {
        int currentTariffGrade = findEntityByBusinessId(contractId).getTariff().getTariffGrade();
        return tariffService.getPossibleTariffs(currentTariffGrade);
    }
}
