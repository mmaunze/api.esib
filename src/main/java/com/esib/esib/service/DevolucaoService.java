package com.esib.esib.service;

import com.esib.esib.modelo.Devolucao;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.repository.DevolucaoRepository;
import com.esib.esib.repository.EmprestimoRepository;
import java.util.Date;
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
public class DevolucaoService {

    private final DevolucaoRepository devolucaoRepository;

    private final EmprestimoRepository emprestimoRepository;

    // CRUD methods

    /**
     *
     * @param devolucao
     * @return
     */

    @Transactional
    public Devolucao create(Devolucao devolucao) {
        // Verifique se o emprestimo associado existe e está ativo
        Emprestimo emprestimo = devolucao.getEmprestimo();
        if (emprestimo == null || !emprestimo.isAtivo()) {
            throw new RuntimeException("Emprestimo não encontrado ou já devolvido");
        }

        // Calcule o atraso (se houver)
        long diasAtraso = calcularDiasAtraso(emprestimo.getDataEmprestimo(), devolucao.getDataDevolucao());
        devolucao.setAtraso(diasAtraso);

        // Atualize o status do emprestimo para devolvido
        emprestimo.setAtivo(false);

        // Salve a devolucao e atualize o emprestimo
        devolucaoRepository.save(devolucao);
        emprestimoRepository.save(emprestimo);

        return devolucao;
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Devolucao> findById(Long id) {
        return devolucaoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Devolucao> findAll() {
        return devolucaoRepository.findAll();
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    public List<Devolucao> findDevolucoesPorBibliotecario(Long bibliotecario) {
        return devolucaoRepository.findByBibliotecario(bibliotecario);
    }

    /**
     *
     * @param devolucao
     * @return
     */
    @Transactional
    public Devolucao update(Devolucao devolucao) {
        return devolucaoRepository.save(devolucao);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        devolucaoRepository.deleteById(id);
    }

    // Method to calculate delay in days
    private long calcularDiasAtraso(Date dataEmprestimo, Date dataDevolucao) {
        long milisegundosNoDia = 24 * 60 * 60 * 1_000;
        return (dataDevolucao.getTime() - dataEmprestimo.getTime()) / milisegundosNoDia;
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    public List<Devolucao> findByBibliotecario(Long bibliotecario) {
        return devolucaoRepository.findByBibliotecario(bibliotecario);
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public List<Devolucao> findByUtilizador(Long utilizador) {
        return devolucaoRepository.findByUtilizador(utilizador);
    }

    /**
     *
     * @param obra
     * @return
     */
    public List<Devolucao> findByObra(Long obra) {
        return devolucaoRepository.findByObra(obra);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Devolucao> findByTitulo(String estado) {
        return devolucaoRepository.findByTitulo(estado);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Devolucao> findByIdioma(String estado) {
        return devolucaoRepository.findByIdioma(estado);
    }

    /**
     *
     * @param areacientifica
     * @return
     */
    public List<Devolucao> findByAreaCientifica(String areacientifica) {
        return devolucaoRepository.findByAreaCientifica(areacientifica);

    }
    private static final Logger LOG = Logger.getLogger(DevolucaoService.class.getName());

}
