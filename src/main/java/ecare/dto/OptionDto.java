package ecare.dto;

import javax.validation.constraints.NotBlank;

public class OptionDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String optionName;

    private Double optionPrice;

    public String getOptionName() {
        return optionName;
    }

    public void setOptionName(String optionName) {
        this.optionName = optionName;
    }

    public Double getOptionPrice() {
        return optionPrice;
    }

    public void setOptionPrice(Double optionPrice) {
        this.optionPrice = optionPrice;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
