package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
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

    public Option() {
    }

    public Option(String optionName, String optionDescription, Double optionPrice) {
        this.optionName = optionName;
        this.optionDescription = optionDescription;
        this.optionPrice = optionPrice;
    }

    public Long getOptionId() {
        return optionId;
    }

    public void setOptionId(Long optionId) {
        this.optionId = optionId;
    }

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

    public String getOptionDescription() {
        return optionDescription;
    }

    public void setOptionDescription(String optionDescription) {
        this.optionDescription = optionDescription;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return optionId.equals(option.optionId) && optionName.equals(option.optionName) && optionDescription.equals(option.optionDescription) && optionPrice.equals(option.optionPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, optionName, optionDescription, optionPrice);
    }
}
