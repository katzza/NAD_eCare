package ecare.dto;


import java.util.List;

public class ContractDto {

    private Long contractId;
    private String businessId;
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
