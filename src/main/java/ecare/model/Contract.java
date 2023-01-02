package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@JsonSerialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "contract")
public class Contract {

    @Id
    @Column(name = "contract_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contractId;

    @Column(name = "business_id", nullable = false, unique = true)
    private String businessId;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "tariff_id")
    private Tariff tariff;

    public Contract(Tariff tariff, String businessId) {
        this.tariff = tariff;
        this.businessId = businessId;
    }

    public Contract() {
    }

    public Long getContractId() {
        return contractId;
    }

    @ManyToMany(cascade = {
            CascadeType.PERSIST, CascadeType.MERGE
    }, fetch = FetchType.EAGER)
    @JoinTable(
            name = "contract_options",
            joinColumns = {
                    @JoinColumn(name = "contract_id")
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

    public void setContractId(Long contractId) {
        this.contractId = contractId;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
        return contractId == contract.contractId && Objects.equals(businessId, contract.businessId) && Objects.equals(tariff, contract.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contractId, businessId, tariff);
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    public Tariff getTariff() {
        return tariff;
    }


}
