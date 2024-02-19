package com.esib.esib.service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.TipoMovimento;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.MovimentoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovimentoService {

    @Autowired
    private MovimentoRepository movimentoRepository;

    // CRUD methods

    @Transactional
    public Movimento criarMovimento(Movimento movimento) {
        // Verifique se as entidades associadas existem
        Bibliotecario bibliotecario = movimento.getIdBibliotecario();
        Obra obra = movimento.getIdObra();
        TipoMovimento tipoMovimento = movimento.getIdTipoMovimento();

        if (bibliotecario == null || obra == null || tipoMovimento == null) {
            throw new RuntimeException("Entidades associadas ao movimento não informadas");
        }

        // Verifique se o tipo de movimento permite a operação pretendida
        if (!tipoMovimento.getPermiteEmprestimo() && movimento.getIdUtilizador() != null) {
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

    public Optional<Movimento> buscarMovimentoPorId(Long id) {
        return movimentoRepository.findById(id);
    }

    public List<Movimento> buscarTodosMovimentos() {
        return movimentoRepository.findAll();
    }

    public List<Movimento> buscarMovimentosPorObra(Long obra) {
        return movimentoRepository.findByObra(obra);
    }

    public List<Movimento> buscarMovimentosPorUtilizador(Long utilizador) {
        return movimentoRepository.findByUtilizador(utilizador);
    }

    @Transactional
    public Movimento atualizarMovimento(Movimento movimento) {
        return movimentoRepository.save(movimento);
    }

    @Transactional
    public void excluirMovimento(Long id) {
        movimentoRepository.deleteById(id);
    }

    // Methods related to relationships

    public Bibliotecario buscarBibliotecarioPorMovimento(Movimento movimento) {
        return movimento.getIdBibliotecario();
    }

    public Obra buscarObraPorMovimento(Movimento movimento) {
        return movimento.getIdObra();
    }

    public TipoMovimento buscarTipoMovimentoPorMovimento(Movimento movimento) {
        return movimento.getIdTipoMovimento();
    }

    public Utilizador buscarUtilizadorPorMovimento(Movimento movimento) {
        return movimento.getIdUtilizador();
    }

}
