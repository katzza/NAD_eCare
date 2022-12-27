package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tariff")
public class Tariff {

    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tariffId;

    @Column(name = "tariff_name", nullable = false, unique = true)
    private String tariffName;

    @Column(name = "tariff_price")
    private Double tariffPrice;

    public Tariff() {
    }

    public Tariff(String name,
                  Double tariffPrice) {
        this.tariffName = name;
        this.tariffPrice = tariffPrice;
    }

    public Long getTariffId() {
        return tariffId;
    }

    public void setTariffId(Long tariffId) {
        this.tariffId = tariffId;
    }

    public String getTariffName() {
        return tariffName;
    }

    public void setTariffName(String tariffName) {
        this.tariffName = tariffName;
    }

    public Double getTariffPrice() {
        return tariffPrice;
    }

    public void setTariffPrice(Double tariffPrice) {
        this.tariffPrice = tariffPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tariff tariff = (Tariff) o;
        return tariffId == tariff.tariffId && Objects.equals(tariffName, tariff.tariffName) && Objects.equals(tariffPrice, tariff.tariffPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffId, tariffName, tariffPrice);
    }

    @ManyToMany(cascade = {
            CascadeType.ALL
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "tariffs_options",
            joinColumns = {
                    @JoinColumn(name = "tariff_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "option_id")
            }
    )
    Set<Option> options = new HashSet<>();

    public Set<Option> getOptions() {
        return options;
    }

    public void setOptions(Set<Option> options) {
        this.options = options;
    }
}
