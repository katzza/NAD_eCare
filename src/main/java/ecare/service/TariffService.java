package ecare.service;

import ecare.dto.TariffDto;
import ecare.model.Tariff;

import ecare.repository.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TariffDto> getAllTariffs() {
        List<Tariff> tariffEntities = tariffRepository.findAll();
        return tariffEntities.stream().map(this::convertToDto).toList();
    }

    public TariffDto findById(Long id) {
        Tariff tariff = tariffRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id.toString()));
        return convertToDto(tariff);
    }

    public TariffDto findByTariffName(String tariffName) {
        Tariff tariff = getEntityByName(tariffName);
        return convertToDto(tariff);
    }

    public Tariff getEntityByName(String tariffName) {
        return tariffRepository.findByTariffName(tariffName).orElseThrow(() -> new EntityNotFoundException(tariffName));
    }

    public Tariff saveTariff(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    public TariffDto convertToDto(Tariff tariffEntity) {
        return modelMapper.map(tariffEntity, TariffDto.class);
    }

    public Tariff convertToEntity(TariffDto tariffDto) {
        return modelMapper.map(tariffDto, Tariff.class);
    }

}
