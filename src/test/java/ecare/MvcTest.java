package ecare;

import ecare.rest.Recource;
import ecare.service.ContractService;
import ecare.service.TariffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(Recource.class)
@AutoConfigureMockMvc
class MvcTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TariffService tariffService;

    @MockBean
    private ContractService contractService;

 /*   @Test
    void testFindAllTariffs() throws Exception {
        TariffDto tariff1 = new TariffDto();
        tariff1.setTariffName("TariffSimple");
        tariff1.setTariffPrice(2.0);
        TariffDto tariff2 = new TariffDto();
        tariff2.setTariffName("TariffBest");
        tariff2.setTariffPrice(12.0);
        List<TariffDto> tariffs = new ArrayList<>();
        tariffs.add(tariff1);
        tariffs.add(tariff2);
        when(tariffService.getAllTariffs()).thenReturn(tariffs);

        mvc.perform(MockMvcRequestBuilders.get("/tariffs"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.size()").value(2))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].tariffName").value("TariffSimple"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[1].tariffName").value("TariffBest"));
    }*/
}
