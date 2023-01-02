package ecare.service;

import ecare.dto.ContractDto;
import ecare.dto.TariffDto;
import ecare.model.ServiceException;
import ecare.model.Tariff;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


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
        Assertions.assertEquals(9, allTariffs.size());
    }

    @Test
    void SaveAndGetTariffById() throws ServiceException {
        Tariff newTariff = tariffService.saveTariff(new Tariff("new", "new Tariff", 5., 1));
        TariffDto tariffById = tariffService.findById(newTariff.getTariffId());
        SoftAssertions sa = new SoftAssertions();
        sa.assertThat(tariffById.getTariffName()).isEqualTo("new");
        sa.assertThat(tariffById.getTariffPrice()).isEqualTo(5.);
        sa.assertThat(tariffById.getOptions()).isEmpty();
        sa.assertAll();
    }

    @Test
    void CreateContract() throws ServiceException {
        TariffDto tariffDto = tariffService.findByTariffName("testS");
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
    void addAndRemoveOptionToContractPositive() throws Exception {
        String optionName = "TestMultiSIM";
        String contractId = "777";
        String new_optionName = "TestHotspotFlat";

        contractService.addOption(contractId, optionName);

        ContractDto contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(1, contractById.getOptions().size());

        contractService.addOption(contractId, optionName);
        contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(1, contractById.getOptions().size());

        contractService.addOption(contractId, new_optionName);
        contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(2, contractById.getOptions().size());

        contractService.removeOption(contractId, new_optionName);
        contractById = contractService.findByBusinessId(contractId);
        Assertions.assertEquals(1, contractById.getOptions().size());
    }

    @Test
    void addOptionToContractValidationError() {
        String optionName = "Spotify";
        String contractId = "777";
        try {
            contractService.addOption(contractId, optionName);
        } catch (Exception ex) {
            Assertions.assertEquals("Bad request: This option is not available in this tariff", ex.getMessage());
        }
    }

    @Test
    void removeOptionToContractNegative() {
        String optionName = "Spotify";
        String contractId = "777";
        try {
            contractService.removeOption(contractId, optionName);
        } catch (Exception ex) {
            Assertions.assertEquals("Bad request: the option is not in the contract and cannot be removed", ex.getMessage());
        }
    }

    @Test
    void changeTariffInContractPositive() throws Exception {
        String contractId = "777";
        String newTariffName = "L";
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
    void changeTariffInContractNegative() throws Exception {
        String contractId = "777";
        String newTariffName = "testXS";
        try {
            contractService.setTariffToContract(contractId, newTariffName);
        } catch (ServiceException ex) {
            Assertions.assertEquals("Bad request: This tariff is not possible for this contract, see contract change rules", ex.getMessage());
        }
    }

    @Test
    void getPossibleTariffs() throws ServiceException {
        int currentTariffGrade = contractService.findByBusinessId("777").getTariff().getTariffGrade();
        List<TariffDto> possibleTariffs = contractService.getTariffsToContract("777");
        List<Integer> possibleTariffGrades = possibleTariffs.stream().map(TariffDto::getTariffGrade).toList();
        SoftAssertions sa = new SoftAssertions();
        sa.assertThatCollection(possibleTariffGrades).doesNotContain(currentTariffGrade, currentTariffGrade);
        sa.assertThatCollection(possibleTariffGrades).contains(currentTariffGrade + 1, currentTariffGrade + 2, currentTariffGrade - 1);
        sa.assertAll();
    }

    @Test
    void notFoundExceptionTest() throws ServiceException {
        String notExistingName = "notExist";
        try {
            optionService.findByOptionName(notExistingName);
        } catch (ServiceException ex) {
            Assertions.assertEquals(ex.getMessage(), "Object not found: Option ID - " + notExistingName);
        }

        try {
            tariffService.findByTariffName(notExistingName);
        } catch (ServiceException ex) {
            Assertions.assertEquals(ex.getMessage(), "Object not found: Tariff - " + notExistingName);
        }

        try {
            contractService.findByBusinessId(notExistingName);
        } catch (ServiceException ex) {
            Assertions.assertEquals(ex.getMessage(), "Object not found: Contract ID- " + notExistingName);
        }
    }

}

