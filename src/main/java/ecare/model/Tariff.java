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

    //  @JsonIgnore
    // @OneToMany(mappedBy = "tariff", fetch = FetchType.LAZY, cascade=CascadeType.ALL)
  /*  private List<Contract> contracts = new ArrayList<>();

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }*/

    /*   @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
       @JoinTable(
               name = "tariff_options",
               joinColumns = { @JoinColumn(name = "tariff_id") },
               inverseJoinColumns = { @JoinColumn(name = "option_id") }
       )*/
    @OneToMany(mappedBy = "tariff")
    private Set<TariffOption> tariffOptions = new HashSet<>();

  /*  public void addOption(Option option) {
        this.tariffOptions.add(option);
        option.getTariffs().add(this);
    }

    public void removeOption(Option option) {
        this.tariffOptions.remove(option);
        option.getTariffs().remove(this);
    }*/

    public Set<TariffOption> getTariffOptions() {
        return tariffOptions;
    }

    public void setTariffOptions(Set<TariffOption> tariffOptions) {
        this.tariffOptions = tariffOptions;
    }
}
