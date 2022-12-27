package ecare.service;

import ecare.model.Option;
import ecare.repository.OptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OptionService {

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public Option saveOption(Option option) {
        return optionRepository.save(option);
    }

  /*  public List<OptionDto> getOptionsByTariffId(String tariffId) {
        List<Option> optionEntities = optionRepository.findByTariff(tariffId);
        return optionEntities.stream().map(this::convertToDto).toList();
    }

    private OptionDto convertToDto(Option optionEntity) {
        return modelMapper.map(optionEntity, OptionDto.class);
    }*/

}
