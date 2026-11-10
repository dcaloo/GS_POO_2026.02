package br.com.fiap.eduardo555933.repository;

import br.com.fiap.eduardo555933.domain.Leitura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeituraRepository extends JpaRepository<Leitura, Long> {

    List<Leitura> findAllByOrderByDataHoraDesc();

    boolean existsByTrechoId(Long trechoId);
}
