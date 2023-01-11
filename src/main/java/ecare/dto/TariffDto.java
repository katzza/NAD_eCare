package ecare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "Price of the tariff", example = "7.5")
    private double tariffPrice;
    @NotBlank
    @Schema(description = "Short name of the tariff", example = "M")
    private String tariffName;

    @NotBlank
    @Schema(description = "Description of the tariff", example = "Tariff M: 128 min 20Gb")
    private String tariffDescription;

    @NotBlank
    @Min(0)
    @Max(10)
    @Schema(description = "Grade of the tariff", example = "2")
    private int tariffGrade;

    private List<OptionDto> options;

}
