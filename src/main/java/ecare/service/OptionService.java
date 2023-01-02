package ecare.service;

import ecare.dto.OptionDto;
import ecare.model.Option;
import ecare.model.ServiceException;
import ecare.repository.OptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    public OptionDto findById(Long optionId) throws ServiceException {
        Option option = optionRepository.findById(optionId).orElseThrow(() -> new ServiceException("Object not found: Option ID - " + optionId, HttpStatus.NOT_FOUND));
        return convertToDto(option);
    }

    public OptionDto findByOptionName(String optionName) throws ServiceException {
        Option option = findEntityByOptionName(optionName);
        return convertToDto(option);
    }

    public Option findEntityByOptionName(String optionName) throws ServiceException {
        return optionRepository.findByOptionName(optionName).orElseThrow(() -> new ServiceException("Object not found: Option ID - " + optionName, HttpStatus.NOT_FOUND));
    }

    private OptionDto convertToDto(Option optionEntity) {
        return modelMapper.map(optionEntity, OptionDto.class);
    }

}
