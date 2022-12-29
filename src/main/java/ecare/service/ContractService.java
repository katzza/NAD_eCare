package ecare.service;

import ecare.dto.ContractDto;
import ecare.model.Contract;
import ecare.model.Option;
import ecare.model.Tariff;
import ecare.repository.ContractRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ContractService {
    @Autowired
    ContractRepository contractRepository;

    @Autowired
    OptionService optionService;

    @Autowired
    private ModelMapper modelMapper;

    public List<ContractDto> getAllContracts() {
        List<Contract> contractEntities = contractRepository.findAll();
        return contractEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public Contract findById(Long contractId) {
        return contractRepository.findById(contractId).orElseThrow(() -> new EntityNotFoundException(contractId.toString()));
    }

    public Contract saveContract(Contract contract) {
        return contractRepository.save(contract);
    }

    public Contract addOption(Long contractId, Long optionId) {
        //todo not found + errors
        Contract contract = contractRepository.findById(contractId).get();
        Option option = optionService.findById(optionId);
        contract.getOptions().add(option);
        return contractRepository.save(contract);
    }

    public ContractDto convertToDto(Contract contractEntity) {
        ContractDto contract = modelMapper.map(contractEntity, ContractDto.class);
        contract.setTariffName(contractEntity.getTariff().getTariffName());
        return contract;
    }
}
