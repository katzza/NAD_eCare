package ecare.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Data
@NoArgsConstructor
@Table(name = "tariff")
public class Tariff implements Serializable {

    @Id
    @Column(name = "tariff_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tariffId;

    @Column(name = "tariff_name", nullable = false, unique = true)
    private String tariffName;

    @Column(name = "tariff_description", nullable = false, unique = true)
    private String tariffDescription;

    @Column(name = "tariff_price", nullable = false)
    private double tariffPrice;

    @Column(name = "tariff_grade", nullable = false)
    private int tariffGrade;


    public Tariff(String tariffName,
                  String tariffDescription,
                  double tariffPrice,
                  int tariffGrade) {
        this.tariffName = tariffName;
        this.tariffDescription = tariffDescription;
        this.tariffPrice = tariffPrice;
        this.tariffGrade = tariffGrade;
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
    private Set<Option> options = new HashSet<>();

}

