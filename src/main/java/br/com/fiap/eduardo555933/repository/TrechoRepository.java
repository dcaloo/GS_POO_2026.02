package br.com.fiap.eduardo555933.repository;

import br.com.fiap.eduardo555933.domain.Trecho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TrechoRepository extends JpaRepository<Trecho, Long> {
    List<Trecho> findAllByOrderByLogradouroAsc();

    boolean existsByBairroId(Long bairroId);
}
