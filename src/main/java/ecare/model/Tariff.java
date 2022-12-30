package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "tariff")
public class Tariff implements Serializable {

    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tariffId;

    @Column(name = "tariff_name", nullable = false, unique = true)
    private String tariffName;

    @Column(name = "tariff_price", nullable = false)
    private double tariffPrice;

    @Column(name = "tariff_grade", nullable = false)
    private int tariffGrade;

    public Tariff() {
    }

    public Tariff(String name,
                  double tariffPrice,
                  int tariffGrade) {
        this.tariffName = name;
        this.tariffPrice = tariffPrice;
        this.tariffGrade = tariffGrade;
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
        return Double.compare(tariff.tariffPrice, tariffPrice) == 0 && tariffGrade == tariff.tariffGrade && tariffId.equals(tariff.tariffId) && tariffName.equals(tariff.tariffName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tariffId, tariffName, tariffPrice, tariffGrade);
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "tariff_option",
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

    public int getTariffGrade() {
        return tariffGrade;
    }

    public void setTariffGrade(int tariffGrade) {
        this.tariffGrade = tariffGrade;
    }
}

