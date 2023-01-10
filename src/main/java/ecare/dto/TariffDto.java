package ecare.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class TariffDto {

    @NotBlank
    private Long tariffId;
    @NotBlank
    @Min(1)
    @Max(50)
    private double tariffPrice;
    @NotBlank
    private String tariffName;

    @NotBlank
    private String tariffDescription;

    @NotBlank
    @Min(0)
    @Max(10)
    private int tariffGrade;

    private List<OptionDto> options;

}
