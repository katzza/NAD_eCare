package ecare.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class OptionDto {

    @NotBlank
    private Long id;

    @NotBlank
    private String optionName;

    @NotBlank
    private String optionDescription;

    private Double optionPrice;

}
