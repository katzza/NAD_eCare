package ecare.service;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Tariff;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@SpringBootTest
@Sql("classpath:test-data.sql")
@Transactional  //clear DB after test
@AutoConfigureTestDatabase
class ContractServiceTest {
    @Autowired
    private ContractService contractService;

    @Autowired
    private TariffService tariffService;

    @Autowired
    private OptionService optionService;

    @Test
    void GetAllTariffs() {
        var allTariffs = tariffService.getAllTariffs();
        Assertions.assertEquals(allTariffs.size(), 6);
    }

    @Test
    void SaveAndGetTariffById() throws Exception {
        Tariff newTariff = tariffService.saveTariff(new Tariff("newTariff", 5.));
        TariffDto tariffById = tariffService.findById(newTariff.getTariffId());
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(tariffById.getTariffName()).isEqualTo("newTariff");
        sa.assertThat(tariffById.getTariffPrice()).isEqualTo(5.);
        sa.assertThat(tariffById.getOptions()).isEmpty();
        sa.assertAll();
    }

    @Test
    void CreateContract() throws Exception {
        TariffDto tariffDto = tariffService.findByTariffName("test-S");
        ContractDto newContract = new ContractDto();
        String businessId = "3245";
        newContract.setBusinessId(businessId);
        newContract.setTariff(tariffDto);
        contractService.createContract(newContract);
        ContractDto contractById = contractService.findByBusinessId(businessId);
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(contractById.getBusinessId()).isEqualTo(businessId);
        sa.assertThat(contractById.getTariff().getTariffName()).isEqualTo(newContract.getTariff().getTariffName());
        sa.assertAll();
    }

    @Test
    void addOptionToContract() {
        String optionName = "Test-Hotspot Flat";
        String contractId = "777";
        String new_optionName = "Test-Spotify";

        contractService.addOption(contractId, optionName);

        ContractDto contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(contractById.getOptions().size(), 1);

        contractService.addOption(contractId, optionName);
        contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(contractById.getOptions().size(), 1);

        contractService.addOption(contractId, new_optionName);
        contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(contractById.getOptions().size(), 2);
    }

    @Test
    void changeTariffInContract() throws Exception {
        String contractId = "777";
        String newTariffName = "test-L";
        ContractDto contractById = contractService.findByBusinessId(contractId);
        String oldTariffName = contractById.getTariff().getTariffName();

        contractService.setTariffToContract(contractId, newTariffName);

        contractById = contractService.findByBusinessId(contractId);
        String currentTariffName = contractById.getTariff().getTariffName();

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(contractById.getOptions().size()).isEqualTo(0);
        sa.assertThat(currentTariffName).isNotEqualTo(oldTariffName);
        sa.assertThat(currentTariffName).isEqualTo(newTariffName);
        sa.assertAll();
    }

    @Test
    void notFoundExceptionTest() {
        String notExistingName = "notExist";
        try {
            optionService.findByOptionName(notExistingName);
        } catch (EntityNotFoundException ex){
            Assertions.assertEquals(ex.getMessage(), notExistingName);
        }

        try {
            tariffService.findByTariffName(notExistingName);
        } catch (EntityNotFoundException ex){
            Assertions.assertEquals(ex.getMessage(), notExistingName);
        }

        try {
            contractService.findByBusinessId(notExistingName);
        } catch (EntityNotFoundException ex){
            Assertions.assertEquals(ex.getMessage(), notExistingName);
        }
    }
}

