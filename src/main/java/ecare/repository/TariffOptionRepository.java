package ecare.repository;

import ecare.model.TariffOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TariffOptionRepository extends JpaRepository<TariffOption, Long> {
    List<TariffOption> findByTariff(Long tariff);
}
