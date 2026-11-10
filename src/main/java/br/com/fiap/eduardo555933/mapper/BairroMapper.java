package br.com.fiap.eduardo555933.mapper;

import br.com.fiap.eduardo555933.domain.Bairro;
import br.com.fiap.eduardo555933.dto.BairroDTO;
import org.springframework.stereotype.Component;

@Component
public class BairroMapper {
    public Bairro toEntity(BairroDTO dto) {
        Bairro bairro = new Bairro();

        bairro.setNome(dto.getNome());
        bairro.setZona(dto.getZona());
        bairro.setPopulacao(dto.getPopulacao());

        return bairro;
    }

    public BairroDTO toDTO(Bairro bairro) {
        BairroDTO dto = new BairroDTO();

        dto.setId(bairro.getId());
        dto.setNome(bairro.getNome());
        dto.setZona(bairro.getZona());
        dto.setPopulacao(bairro.getPopulacao());

        return dto;
    }
}
