package com.esib.esib.service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.EmprestimoRepository;
import com.esib.esib.repository.ObraRepository;
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
public class EmprestimoService {
    /**
     *
     */
    private static final Logger LOG = Logger.getLogger(EmprestimoService.class.getName());

    /**
     *
     */
    private final EmprestimoRepository emprestimoRepository;

    /**
     *
     */
    private final ObraRepository obraRepository;

    // CRUD methods

    /**
     *
     * @param emprestimo
     * @return
     */

    @Transactional
    public Emprestimo create(Emprestimo emprestimo) {
        // Verifique se todos os dados obrigatórios estão presentes
        if (emprestimo.getBibliotecario() == null ||
                emprestimo.getEstado() == null ||
                emprestimo.getObra() == null ||
                emprestimo.getUtilizador() == null) {
            throw new RuntimeException("Dados obrigatórios do emprestimo não informados");
        }

        // Verifique se a obra está disponível
        Obra obra = emprestimo.getObra();
        if (!obra.getEstado().getDescricao().equalsIgnoreCase("disponivel")) {
            throw new RuntimeException("Obra indisponível para empréstimo");
        }

        // Defina a data para devolução e calcule o atraso inicial (zero)
        emprestimo.setDataParaDevolucao(calcularDataParaDevolucao(4_320));
        emprestimo.setAtraso(0);

        // Atualize a quantidade disponível da obra
        obra.setDisponivel(false);
        obraRepository.save(obra);

        // Salve o emprestimo
        emprestimoRepository.save(emprestimo);

        return emprestimo;
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Emprestimo> findById(Long id) {
      return emprestimoRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    /**
     *
     * @param bibliotecario
     * @return
     */
    public List<Emprestimo> findByBibliotecario(Long bibliotecario) {
        return emprestimoRepository.findByBibliotecario(bibliotecario);
    }

    /**
     *
     * @param utilizador
     * @return
     */
    public List<Emprestimo> findByUtilizador(Long utilizador) {
        return emprestimoRepository.findByUtilizador(utilizador);
    }

    /**
     *
     * @param obra
     * @return
     */
    public List<Emprestimo> findByObra(Long obra) {
        return emprestimoRepository.findByObra(obra);
    }
    
    /**
     *
     * @param titulo
     * @return
     */
    public List<Emprestimo> findByTitulo(String titulo) {
        return emprestimoRepository.findByTitulo(titulo);
    }

    /**
     *
     * @param idioma
     * @return
     */
    public List<Emprestimo> findByIdioma(String idioma) {
        return emprestimoRepository.findByIdioma(idioma);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Emprestimo> findByAreaCientifica(String estado) {
        return emprestimoRepository.findByAcientifica(estado);
    }

    /**
     *
     * @param emprestimo
     * @return
     */
    @Transactional
    public Emprestimo update(Emprestimo emprestimo) {
        
        var newEmprestimo = new Emprestimo();

        newEmprestimo.setId(emprestimo.getId());
        newEmprestimo.setBibliotecario(emprestimo.getBibliotecario());
        newEmprestimo.setAtraso(emprestimo.getAtraso());
        newEmprestimo.setDataEmprestimo(emprestimo.getDataEmprestimo());
        newEmprestimo.setDataParaDevolucao(emprestimo.getDataParaDevolucao());
        newEmprestimo.setEstado(emprestimo.getEstado());

        return emprestimoRepository.save(newEmprestimo);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        findById(id);
        try {
            emprestimoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(" Impossivel remover Emprestimo " + e);
        }

    }

    // Method to calculate due date

    /**
     *
     * @param prazoEmprestimo
     * @return
     */
    private Date calcularDataParaDevolucao(int prazoEmprestimo) {
        long milisegundosPorDia = 24 * 60 * 60 * 1_000;
        var dataEmprestimo = new Date();
        return new Date(dataEmprestimo.getTime() + prazoEmprestimo * milisegundosPorDia);
    }

    /**
     *
     * @param estado
     * @return
     */
    public List<Emprestimo> findByEstado(String estado) {
        return emprestimoRepository.findByEstado(estado);
    }



}
