package br.com.fiap.eduardo555933.service;

import br.com.fiap.eduardo555933.domain.Bairro;
import br.com.fiap.eduardo555933.domain.Trecho;
import br.com.fiap.eduardo555933.dto.TrechoDTO;
import br.com.fiap.eduardo555933.mapper.TrechoMapper;
import br.com.fiap.eduardo555933.repository.BairroRepository;
import br.com.fiap.eduardo555933.repository.LeituraRepository;
import br.com.fiap.eduardo555933.repository.TrechoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TrechoService {

    private final TrechoRepository trechoRepository;
    private final BairroRepository bairroRepository;
    private final LeituraRepository leituraRepository;
    private final TrechoMapper trechoMapper;

    public List<TrechoDTO> listar() {
        return trechoRepository.findAllByOrderByLogradouroAsc().stream().map(trechoMapper::toDTO).sorted((t1, t2) -> String.CASE_INSENSITIVE_ORDER.compare(t1.getLogradouro(), t2.getLogradouro())).toList();
    }

    public TrechoDTO buscarPorId(Long id) {
        Trecho trecho = trechoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trecho não encontrado"));

        return trechoMapper.toDTO(trecho);
    }

    public TrechoDTO salvar(TrechoDTO dto) {
        Bairro bairro = bairroRepository.findById(dto.getBairroId()).orElseThrow(() -> new NoSuchElementException("Bairro não encontrado"));

        Trecho trecho = trechoMapper.toEntity(dto, bairro);
        trecho = trechoRepository.save(trecho);

        return trechoMapper.toDTO(trecho);
    }

    public TrechoDTO atualizar(Long id, TrechoDTO dto) {
        Trecho trecho = trechoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trecho não encontrado"));

        Bairro bairro = bairroRepository.findById(dto.getBairroId()).orElseThrow(() -> new NoSuchElementException("Bairro não encotrado"));

        trecho.setLogradouro(dto.getLogradouro());
        trecho.setTipoCalcada(dto.getTipoCalcada());
        trecho.setAltitude(dto.getAltitude());
        trecho.setBairro(bairro);

        trecho = trechoRepository.save(trecho);

        return trechoMapper.toDTO(trecho);
    }

    public void excluir(Long id) {
        Trecho trecho = trechoRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Trecho não encontrado"));

        if(leituraRepository.existsByTrechoId(id)) {
            throw new IllegalStateException("Não é possivel excluir um trecho que possui leituras cadastradas");
        }
        trechoRepository.delete(trecho);
    }
}
