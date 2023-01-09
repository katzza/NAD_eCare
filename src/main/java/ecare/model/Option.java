package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@NoArgsConstructor
@Table(name = "option")
public class Option implements Serializable {

    @Id
    @Column(name = "option_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long optionId;

    @Column(name = "option_name", nullable = false, unique = true)
    private String optionName;

    @Column(name = "option_description", nullable = false, unique = true)
    private String optionDescription;

    @Column(name = "option_price")
    private Double optionPrice;

    public Option(String optionName, String optionDescription, Double optionPrice) {
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.optionPrice = optionPrice;
    }
}
