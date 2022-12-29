package ecare.dto;

import java.util.List;
import java.util.Objects;

public class TariffDto {

    private Long tariffId;
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

    public List<OptionDto> getOptions() {
        return options;
    }

    public void setOptions(List<OptionDto> options) {
        this.options = options;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TariffDto tariffDto = (TariffDto) o;
        return tariffPrice.equals(tariffDto.tariffPrice) && tariffName.equals(tariffDto.tariffName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffPrice, tariffName);
    }
}
