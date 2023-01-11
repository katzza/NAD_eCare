package ecare.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@Schema
public class OptionDto {

    @NotBlank
    @Schema(description = "Technical ID")
    private Long id;

    @NotBlank
    @Schema(description = "Short name of the option", example = "MultiSIM")
    private String optionName;

    @NotBlank
    @Schema(description = "Description of the option", example = "Several SIM cards for one contract")
    private String optionDescription;

    @Schema(description = "Price of the option", example = "2.5")
    private Double optionPrice;

}
