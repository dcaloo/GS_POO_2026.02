package br.com.fiap.eduardo555933.service;

import br.com.fiap.eduardo555933.domain.Bairro;
import br.com.fiap.eduardo555933.dto.BairroDTO;
import br.com.fiap.eduardo555933.mapper.BairroMapper;
import br.com.fiap.eduardo555933.repository.BairroRepository;
import br.com.fiap.eduardo555933.repository.TrechoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class BairroService {
    private final BairroRepository bairroRepository;
    private final TrechoRepository trechoRepository;
    private final BairroMapper bairroMapper;

    public List<BairroDTO> listar() {
        return bairroRepository.findAllByOrderByNomeAsc().stream().map(bairroMapper::toDTO).sorted((b1, b2) -> String.CASE_INSENSITIVE_ORDER.compare(b1.getNome(), b2.getNome())).toList();
    }

    public BairroDTO buscarPorId(Long id) {
        Bairro bairro = bairroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Bairro não encontrado"));

        return bairroMapper.toDTO(bairro);
    }

    public BairroDTO salvar(BairroDTO dto) {
        Bairro bairro = bairroMapper.toEntity(dto);
        bairro = bairroRepository.save(bairro);

        return bairroMapper.toDTO(bairro);
    }

    public BairroDTO atualizar(Long id, BairroDTO dto) {
        Bairro bairro = bairroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Bairro não encontrado"));

        bairro.setNome(dto.getNome());
        bairro.setZona(dto.getZona());
        bairro.setPopulacao(dto.getPopulacao());

        bairro = bairroRepository.save(bairro);
        return bairroMapper.toDTO(bairro);
    }

    public void excluir(Long id) {
        Bairro bairro = bairroRepository.findById(id).orElseThrow(() -> new NoSuchElementException("Bairro não encontrado"));

        if(trechoRepository.existsByBairroId(id)) {
            throw new IllegalStateException("Não é possivel excluir um bairro que possui trechos cadastrados");
        }

        bairroRepository.delete(bairro);
    }
}
