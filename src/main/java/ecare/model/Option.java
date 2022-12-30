package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

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

    @Column(name = "option_price")
    private Double optionPrice;

    public Option() {
    }

    public Option(String optionName, Double optionPrice) {
        this.optionName = optionName;
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

  /*  @ManyToMany(mappedBy = "options")
    private Set<Tariff> tariffs = new HashSet<>();

    public Set<Tariff> getTariffs() {
        return tariffs;
    }

    public void setTariffs(Set<Tariff> tariffs) {
        this.tariffs = tariffs;
    }*/

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Option option = (Option) o;
        return optionId.equals(option.optionId) && optionName.equals(option.optionName) && optionPrice.equals(option.optionPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(optionId, optionName, optionPrice);
    }
}
