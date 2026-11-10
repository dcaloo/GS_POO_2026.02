package br.com.fiap.eduardo555933.repository;

import br.com.fiap.eduardo555933.domain.Bairro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BairroRepository extends JpaRepository<Bairro, Long> {

    List<Bairro> findAllByOrderByNomeAsc();
}
