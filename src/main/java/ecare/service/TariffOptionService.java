package ecare.service;

import ecare.model.Option;
import ecare.model.Tariff;
import ecare.model.TariffOption;
import ecare.repository.TariffOptionRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TariffOptionService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private TariffOptionRepository tariffOptionRepository;

    public TariffOption saveTariffOption(Tariff tariff, Option option) {
        return tariffOptionRepository.save(new TariffOption(tariff, option));
    }

    public List <TariffOption> getByTariffId(Long tariffId){
        return tariffOptionRepository.findByTariff(tariffId);
    }
}
