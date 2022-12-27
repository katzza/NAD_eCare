package ecare.dto;

import java.util.List;

public class TariffDto {

    private Double tariffPrice;
    private String tariffName;

    private List<OptionDto> options;

    public Double getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(Double tariffPrice) {
        this.tariffPrice = tariffPrice;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }
}
