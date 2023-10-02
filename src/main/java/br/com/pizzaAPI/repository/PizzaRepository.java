package br.com.pizzaAPI.repository;

import br.com.pizzaAPI.entity.PizzaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    Optional<PizzaEntity> findById(int id);
}
