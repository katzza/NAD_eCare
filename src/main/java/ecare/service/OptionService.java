package ecare.service;

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

    public Option findById(Long optionId) {
        return optionRepository.findById(optionId).orElseThrow(() -> new EntityNotFoundException(optionId.toString()));
    }

  /*  public List<OptionDto> getOptionsByTariffId(String tariffId) {
        List<Option> optionEntities = optionRepository.findByTariff(tariffId);
        return optionEntities.stream().map(this::convertToDto).toList();
    }

    private OptionDto convertToDto(Option optionEntity) {
        return modelMapper.map(optionEntity, OptionDto.class);
    }*/

}
