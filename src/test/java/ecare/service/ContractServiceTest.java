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

    @Test
    void GetAllTariffs() {
        var allTariffs = tariffService.getAllTariffs();
        Assertions.assertEquals(allTariffs.size(), 6);
    }

    @Test
    void SaveAndGetTariffById() throws Exception {
        Tariff newTariff = tariffService.saveTariff(new Tariff("newTariff", 5.));
        TariffDto tariffById = tariffService.getById(newTariff.getTariffId());
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(tariffById.getTariffName()).isEqualTo("newTariff");
        sa.assertThat(tariffById.getTariffPrice()).isEqualTo(5.);
        sa.assertThat(tariffById.getOptions()).isEmpty();
    }

    @Test
    void CreateContract() throws Exception {
        TariffDto tariffDto = tariffService.getById(12L);
        ContractDto contractDto = new ContractDto();
        contractDto.setBusinessId("3245");
        contractDto.setTariff(tariffDto);
        Contract contract = contractService.createContract(contractDto);


    }
}

