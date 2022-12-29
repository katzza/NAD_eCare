package ecare.dto;


import java.util.List;

public class ContractDto {

    private int contractId;
    private String businessId;
    private TariffDto tariffDto;

    private List<OptionDto> options;

    public int getContractId() {
        return contractId;
    }

    public void setContractId(int contractId) {
        this.contractId = contractId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public TariffDto getTariffDto() {
        return tariffDto;
    }

    public void setTariffDto(TariffDto tariffDto) {
        this.tariffDto = tariffDto;
    }

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }
}
