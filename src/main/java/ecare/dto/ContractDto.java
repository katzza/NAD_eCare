package ecare.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Schema
public class ContractDto {

    @NotBlank
    @Schema(description = "technical ID")
    private Long contractId;
    @NotBlank
    @Schema(description = "business ID", example = "32647")
    private String businessId;
    @NotNull
    private TariffDto tariff;

    private List<OptionDto> options;

}
