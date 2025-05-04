package br.com.pizzaAPI.ports.output.repository;

import br.com.pizzaAPI.adapters.output.entity.PizzaEntity;
import br.com.pizzaAPI.domain.entity.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PizzaRepository extends JpaRepository<PizzaEntity, Long> {
    Optional<PizzaEntity> findById(long id);  // Alterado para long, pois é o tipo correto para o ID
    void deleteById(long id);  // Alterado para long, pois é o tipo correto para o ID
    List<PizzaEntity> findAll();
}
