package com.esib.esib.service;

import com.esib.esib.modelo.Bibliotecario;
import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.TipoMovimento;
import com.esib.esib.repository.MovimentoRepository;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import javax.transaction.Transactional;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 *
 * @author Meldo Maunze
 */
@Service
@RequiredArgsConstructor
@Data
public class MovimentoService {

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(MovimentoService.class.getName());

    /**
     *
     */
    private final MovimentoRepository movimentoRepository;

    // CRUD methods
    /**
     *
     * @param movimento
     * @return
     */
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

    /**
     *
     * @param id
     * @return
     */
    public Optional<Movimento> findById(Long id) {
        return movimentoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Movimento> findAll() {
        return movimentoRepository.findAll();
    }

    /**
     *
     * @param movimento
     * @return
     */
    @Transactional
    public Movimento update(Movimento movimento) {
        return movimentoRepository.save(movimento);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        movimentoRepository.deleteById(id);
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    public List<Movimento> findByBibliotecario(Long bibliotecario) {
        return movimentoRepository.findByBibliotecario(bibliotecario);
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public List<Movimento> findByUtilizador(Long utilizador) {
        return movimentoRepository.findByUtilizador(utilizador);
    }

    /**
     *
     * @param obra
     * @return
     */
    public List<Movimento> findByObra(Long obra) {
        return movimentoRepository.findByObra(obra);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Movimento> findByTitulo(String estado) {
        return movimentoRepository.findByTitulo(estado);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Movimento> findByIdioma(String estado) {
        return movimentoRepository.findByIdioma(estado);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Movimento> findByAreaCientifica(String estado) {
        return movimentoRepository.findByAcientifica(estado);
    }

    public Object gerarRelatorio() {

       return "relatorios";
       
    }

}
