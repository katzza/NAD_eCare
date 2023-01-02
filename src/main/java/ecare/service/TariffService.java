package ecare.service;

import ecare.dto.TariffDto;
import ecare.model.ServiceException;
import ecare.model.Tariff;

import ecare.repository.TariffRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public class TariffService {

    @Autowired
    TariffRepository tariffRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<TariffDto> getAllTariffs() {
        var tariffEntities = tariffRepository.findAll();
        return tariffEntities.stream().map(this::convertToDto).toList();
    }

    public TariffDto findById(Long id) throws ServiceException {
        var tariff = tariffRepository.findById(id).orElseThrow(() -> new ServiceException("Object not found: Tariff ID - " + id, HttpStatus.NOT_FOUND));
        return convertToDto(tariff);
    }

    public TariffDto findByTariffName(String tariffName) throws ServiceException {
        var tariff = findEntityByName(tariffName);
        return convertToDto(tariff);
    }

    public Tariff findEntityByName(String tariffName) throws ServiceException {
        return tariffRepository.findByTariffName(tariffName).orElseThrow(() -> new ServiceException("Object not found: Tariff - " + tariffName, HttpStatus.NOT_FOUND));
    }

    public Tariff saveTariff(Tariff tariff) {
        return tariffRepository.save(tariff);
    }

    public List<TariffDto> getPossibleTariffs(int currentTariffGrade) {
        var tariffEntities = getPossibleTariffEntities(currentTariffGrade);
        return tariffEntities.stream().map(this::convertToDto).toList();
    }

    public List<Tariff> getPossibleTariffEntities(int currentTariffGrade) {
        int previousTariffGrade = currentTariffGrade - 1;
        return tariffRepository.findPossibleTariffs(previousTariffGrade, currentTariffGrade);
    }

    public TariffDto convertToDto(Tariff tariffEntity) {
        return modelMapper.map(tariffEntity, TariffDto.class);
    }

    public Tariff convertToEntity(TariffDto tariffDto) {
        return modelMapper.map(tariffDto, Tariff.class);
    }


}
