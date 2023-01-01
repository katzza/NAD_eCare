package ecare.dto;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public class ContractDto {

    @NotBlank
    private Long contractId;
    @NotBlank
    private String businessId;
    @NotNull
    private TariffDto tariff;

    private List<OptionDto> options;

    public Long getContractId() {
        return contractId;
    }

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public TariffDto getTariff() {
        return tariff;
    }

    public void setTariff(TariffDto tariff) {
        this.tariff = tariff;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }


}
