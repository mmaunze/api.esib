package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.TipoMovimento;
import com.esib.esib.repository.MovimentoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class MovimentoService {

    private final MovimentoRepository movimentoRepository;

    // CRUD methods

    @Transactional
    public Movimento create(Movimento movimento) {
        // Verifique se as entidades associadas existem
        Bibliotecario bibliotecario = movimento.getBibliotecario();
        Obra obra = movimento.getObra();
        TipoMovimento tipoMovimento = movimento.getTipoMovimento();

        if (bibliotecario == null || obra == null || tipoMovimento == null) {
            throw new RuntimeException("Entidades associadas ao movimento não informadas");
        }

        // Verifique se o tipo de movimento permite a operação pretendida
        if (!tipoMovimento.getPermiteEmprestimo() && movimento.getUtilizador() != null) {
            throw new RuntimeException("Tipo de movimento não permite empréstimo");
        }

        // Verifique disponibilidade da obra para empréstimo (se aplicável)
        if (tipoMovimento.getPermiteEmprestimo() && obra.getDisponivel() == false) {
            throw new RuntimeException("Obra indisponível para empréstimo");
        }

        // Atualize a disponibilidade da obra (se aplicável)
        if (tipoMovimento.getPermiteEmprestimo()) {
            obra.setDisponivel(!obra.getDisponivel());
        }

        // Salve o movimento
        return movimentoRepository.save(movimento);
    }

    public Optional<Movimento> findById(Long id) {
        return movimentoRepository.findById(id);
    }

    public List<Movimento> findAll() {
        return movimentoRepository.findAll();
    }

    @Transactional
    public Movimento update(Movimento movimento) {
        return movimentoRepository.save(movimento);
    }

    @Transactional
    public void delete(Long id) {
        movimentoRepository.deleteById(id);
    }

    public List<Movimento> findByBibliotecario(Long bibliotecario) {
        return movimentoRepository.findByBibliotecario(bibliotecario);
    }

    public List<Movimento> findByUtilizador(Long utilizador) {
        return movimentoRepository.findByUtilizador(utilizador);
    }

    public List<Movimento> findByObra(Long obra) {
        return movimentoRepository.findByObra(obra);
    }



    public List<Movimento> findByTitulo(String estado) {
        return movimentoRepository.findByTitulo(estado);
    }

    public List<Movimento> findByIdioma(String estado) {
        return movimentoRepository.findByIdioma(estado);
    }

    public List<Movimento> findByAreaCientifica(String estado) {
        return movimentoRepository.findByAcientifica(estado);
    }

}
