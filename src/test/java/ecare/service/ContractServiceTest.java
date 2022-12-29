package ecare.service;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.Contract;
import ecare.model.Tariff;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;


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
        TariffDto tariffDto = tariffService.findById(12L);
        ContractDto newContract = new ContractDto();
        newContract.setBusinessId("3245");
        newContract.setTariff(tariffDto);
        Contract contract = contractService.createContract(newContract);
        ContractDto contractById = contractService.findById(contract.getContractId());
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(contractById.getContractId()).isEqualTo(contract.getContractId());
        sa.assertThat(contractById.getBusinessId()).isEqualTo(newContract.getBusinessId());
        sa.assertThat(contractById.getTariff().getTariffName()).isEqualTo(newContract.getTariff().getTariffName());
        sa.assertAll();
    }

    @Test
    void addOptionToContract() {
        Long optionId = 22L;
        Long contractId = 31L;
        Long new_optionId = 23L;

        contractService.addOption(contractId, optionId);

        ContractDto contractById = contractService.findById(contractId);
        Assertions.assertEquals(contractById.getOptions().size(), 1);

        contractService.addOption(contractId, optionId);
        contractById = contractService.findById(contractId);
        Assertions.assertEquals(contractById.getOptions().size(), 1);

        contractService.addOption(contractId, new_optionId);
        contractById = contractService.findById(contractId);
        Assertions.assertEquals(contractById.getOptions().size(), 2);
    }

    @Test
    void changeTariffInContract() throws Exception {
        Long contractId = 31L;
        Long newTariffId = 11L;
        ContractDto contractById = contractService.findById(contractId);
        Long oldTariffId = contractById.getTariff().getTariffId();

        contractService.setTariffToContract(contractId, newTariffId);

        contractById = contractService.findById(contractId);

        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(contractById.getOptions().size()).isEqualTo(0);
        sa.assertThat(contractById.getTariff().getTariffId()).isNotEqualTo(oldTariffId);
        sa.assertThat(contractById.getTariff().getTariffId()).isEqualTo(newTariffId);
        sa.assertAll();
    }
}

