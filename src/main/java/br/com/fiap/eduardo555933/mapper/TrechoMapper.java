package br.com.fiap.eduardo555933.mapper;

import br.com.fiap.eduardo555933.domain.Bairro;
import br.com.fiap.eduardo555933.domain.Trecho;
import br.com.fiap.eduardo555933.dto.TrechoDTO;
import org.springframework.stereotype.Component;

@Component
public class TrechoMapper {
    public Trecho toEntity(TrechoDTO dto, Bairro bairro) {
        Trecho trecho = new Trecho();

        trecho.setLogradouro(dto.getLogradouro());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
        trecho.setBairro(bairro);

        return trecho;
    }

    public TrechoDTO toDTO(Trecho trecho) {
        TrechoDTO dto = new TrechoDTO();

        dto.setId(trecho.getId());
        dto.setLogradouro(trecho.getLogradouro());
        dto.setTipoCalcada(trecho.getTipoCalcada());
        dto.setAltitude(trecho.getAltitude());
        dto.setBairroId(trecho.getBairro().getId());

        return dto;
    }
}
