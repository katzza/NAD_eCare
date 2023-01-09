package ecare.dto;


import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class ContractDto {

    @NotBlank
    private Long contractId;
    @NotBlank
    private String businessId;
    @NotNull
    private TariffDto tariff;

    private List<OptionDto> options;

}
