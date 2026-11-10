package br.com.fiap.eduardo555933.service;

import br.com.fiap.eduardo555933.domain.Leitura;
import br.com.fiap.eduardo555933.domain.Trecho;
import br.com.fiap.eduardo555933.dto.LeituraDTO;
import br.com.fiap.eduardo555933.mapper.LeituraMapper;
import br.com.fiap.eduardo555933.repository.LeituraRepository;
import br.com.fiap.eduardo555933.repository.TrechoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class LeituraService {
    private final LeituraRepository leituraRepository;
    private final TrechoRepository trechoRepository;
    private final LeituraMapper leituraMapper;

    private String calcularSituacao(Integer lamina) {
        if(lamina < 6) {
            return "NORMAL";
        } else if (lamina < 12) {
            return "ATENCAO";
        } else {
            return "ALERTA";
        }
    }

    public List<LeituraDTO> listar() {
        return leituraRepository.findAllByOrderByDataHoraDesc().stream().map(leituraMapper::toDTO).toList();
    }

    public LeituraDTO buscarPorId(Long id) {
        Leitura leitura = leituraRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Leitura não encontrada"));

        return leituraMapper.toDTO(leitura);
    }

    public LeituraDTO salvar(LeituraDTO dto) {
        Trecho trecho = trechoRepository.findById(dto.getTrechoId()).orElseThrow(() -> new NoSuchElementException("Trecho não encontrado"));

        Leitura leitura = leituraMapper.toEntity(dto, trecho);
        leitura.setSituacao(calcularSituacao(dto.getLamina()));

        leitura = leituraRepository.save(leitura);

        return leituraMapper.toDTO(leitura);
    }

    public LeituraDTO atualizar(Long id, LeituraDTO dto) {
        Leitura leitura = leituraRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Leitura não encontrada"));

        Trecho trecho = trechoRepository.findById(dto.getTrechoId()).orElseThrow(() -> new NoSuchElementException("Trecho não encontrado"));

        leitura.setDataHora(dto.getDataHora());
        leitura.setLamina(dto.getLamina());
        leitura.setTrecho(trecho);
        leitura.setSituacao(calcularSituacao(dto.getLamina()));

        leitura = leituraRepository.save(leitura);

        return leituraMapper.toDTO(leitura);
    }

    public void excluir(Long id) {
        Leitura leitura = leituraRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Leitura não encontrada"));

        leituraRepository.delete(leitura);
    }
}
