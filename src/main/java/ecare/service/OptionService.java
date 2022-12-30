package ecare.service;

import ecare.dto.OptionDto;
import ecare.model.Option;
import ecare.repository.OptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@Service
public class OptionService {

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

    public OptionDto findById(Long optionId) {
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new EntityNotFoundException(optionId.toString()));
        return convertToDto(option);
    }

    public OptionDto findByOptionId(Long optionId) {
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new EntityNotFoundException(optionId.toString()));
        return convertToDto(option);
    }

    public OptionDto findByOptionName(String optionName) {
        Option option = optionRepository.findByOptionName(optionName).orElseThrow(() -> new EntityNotFoundException(optionName));
        return convertToDto(option);
    }

    public Option findEntityByOptionName(String optionName) {
        return optionRepository.findByOptionName(optionName).orElseThrow(() -> new EntityNotFoundException(optionName));
    }

  /*  public List<OptionDto> getOptionsByTariffId(String tariffId) {
        List<Option> optionEntities = optionRepository.findByTariff(tariffId);
        return optionEntities.stream().map(this::convertToDto).toList();
    }*/

    private OptionDto convertToDto(Option optionEntity) {
        return modelMapper.map(optionEntity, OptionDto.class);
    }

}
