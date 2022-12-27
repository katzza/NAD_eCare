package ecare.service;

import ecare.dto.TariffDto;
import ecare.model.Option;
import ecare.model.Tariff;
import ecare.model.TariffOption;
import ecare.repository.OptionRepository;
import ecare.repository.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TariffDto> getAllTariffs() {
        List<Tariff> tariffEntities = tariffRepository.findAll();
        return tariffEntities.stream().map(this::convertToDto).toList();
    }

    public TariffDto getById(Long id) throws NotFoundException {
        Optional<Tariff> tariffEntity = tariffRepository.findById(id);
        if (tariffEntity.isPresent()) {
            List<Option> options = getOptionsToTariff(tariffEntity.get());

            //    optionRepository.findAllById(tariffEntity.get().getTariffOptions())
            return convertToDto(tariffEntity.get());
        } else {
            throw new NotFoundException("Error 404 Not found");
        }
    }

    private List<Option> getOptionsToTariff(Tariff tariffEntity) {
        List<TariffOption> optionIds = tariffEntity.getTariffOptions().stream().toList();
        List<Option> options = new ArrayList<>();
        for (TariffOption optionId : optionIds
        ) {
            options.add(optionId.getOption());
        }
        return options;
    }

  /* public Tariff addOption(Tariff tariff, Option option) {
        tariff.addOption(option);
      //  optionRepository.save(option);
        return tariffRepository.save(tariff);
    }*/

    public Tariff addTariff(TariffDto tariffDto) {
        Tariff newTariff = new Tariff(tariffDto.getTariffName(), tariffDto.getTariffPrice());
        return tariffRepository.save(newTariff);
    }

    public Tariff saveTariff(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    public void deleteById(Long id) {
        tariffRepository.deleteById(id);
    }

    private TariffDto convertToDto(Tariff tariffEntity) {
        TariffDto tariff = modelMapper.map(tariffEntity, TariffDto.class);
        // Set<Option> options = tariffEntity.getTariffOptions();
        //  List<OptionDto> options = (.stream().map(modelMapper.map(x, OptionDto.class)) );
        return tariff;
    }

}
