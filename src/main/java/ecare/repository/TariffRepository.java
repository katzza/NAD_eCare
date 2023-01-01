package ecare.repository;

import ecare.model.Tariff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TariffRepository extends JpaRepository<Tariff, Long> {
    Optional<Tariff> findByTariffName(String tariffName);

    @Query("SELECT t FROM Tariff t WHERE t.tariffGrade = ?1 or t.tariffGrade > ?2")
    List<Tariff> findPossibleTariffs(int previousGrade, int currentGrade);

}

