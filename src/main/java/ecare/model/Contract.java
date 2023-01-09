package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@JsonSerialize
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@NoArgsConstructor
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

}
