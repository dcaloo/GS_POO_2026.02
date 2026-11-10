package br.com.fiap.eduardo555933.mapper;

import br.com.fiap.eduardo555933.domain.Leitura;
import br.com.fiap.eduardo555933.domain.Trecho;
import br.com.fiap.eduardo555933.dto.LeituraDTO;
import org.springframework.stereotype.Component;

@Component
public class LeituraMapper {

    public Leitura toEntity(LeituraDTO dto, Trecho trecho) {
        Leitura leitura = new Leitura();

        leitura.setDataHora(dto.getDataHora());
        leitura.setLamina(dto.getLamina());
        leitura.setTrecho(trecho);

        return leitura;
    }

    public LeituraDTO toDTO(Leitura leitura) {
        LeituraDTO dto = new LeituraDTO();

        dto.setId(leitura.getId());
        dto.setDataHora(leitura.getDataHora());
        dto.setLamina(leitura.getLamina());
        dto.setSituacao(leitura.getSituacao());
        dto.setTrechoId(leitura.getTrecho().getId());

        return dto;
    }
}
