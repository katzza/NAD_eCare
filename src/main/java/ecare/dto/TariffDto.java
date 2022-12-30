package ecare.dto;

import java.util.List;
import java.util.Objects;

public class TariffDto {

    private Long tariffId;
    private double tariffPrice;
    private String tariffName;

    private int tariffGrade;

    private List<OptionDto> options;

    public double getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(double tariffPrice) {
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
        return Double.compare(tariffDto.tariffPrice, tariffPrice) == 0 && tariffId.equals(tariffDto.tariffId) && tariffName.equals(tariffDto.tariffName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffId, tariffPrice, tariffName);
    }

    public int getTariffGrade() {
        return tariffGrade;
    }

    public void setTariffGrade(int tariffGrade) {
        this.tariffGrade = tariffGrade;
    }
}
