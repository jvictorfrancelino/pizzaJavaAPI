package br.com.pizzaAPI.repository;

import br.com.pizzaAPI.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    Optional<PizzaEntity> findById(int id);

    @Modifying
    @Transactional
    @Query("DELETE FROM PizzaEntity e WHERE e.id = ?1")
    void deleteById(Integer id);
}
