package ecare.service;

import ecare.dto.TariffDto;
import ecare.model.Option;
import ecare.model.Tariff;

import ecare.repository.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TariffDto> getAllTariffs() {
        List<Tariff> tariffEntities = tariffRepository.findAll();
        return tariffEntities.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public TariffDto getById(Long id) throws Exception {
        Optional<Tariff> tariffEntity = tariffRepository.findById(id);
        if (tariffEntity.isPresent()) {
            return convertToDto(tariffEntity.get());
        } else {
            throw new Exception("Error 404 Not found");
        }
    }

    public Tariff getEntityById(Long id) throws Exception {
        Optional<Tariff> tariffEntity = tariffRepository.findById(id);
        if (tariffEntity.isPresent()) {
            return tariffEntity.get();
        } else {
            throw new Exception("Error 404 Not found");
        }
    }


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

    public TariffDto convertToDto(Tariff tariffEntity) {
        TariffDto tariff = modelMapper.map(tariffEntity, TariffDto.class);
        return tariff;
    }

    public Tariff convertToEntity(TariffDto tariffDto) {
        Tariff tariff = modelMapper.map(tariffDto, Tariff.class);
        return tariff;
    }

}
