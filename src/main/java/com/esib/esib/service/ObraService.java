package com.esib.esib.service;

import com.esib.esib.modelo.AreaCientifica;
import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Idioma;
import com.esib.esib.modelo.Movimento;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.TipoObra;
import com.esib.esib.repository.ObraRepository;
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
public class ObraService {
    // Methods related

    /**
     *
     */
    private static final Logger logger = Logger.getLogger(ObraService.class.getName());

    /**
     *
     */
    private final ObraRepository obraRepository;

    // CRUD methods
    /**
     *
     * @param obra
     * @return
     */
    @Transactional
    public Obra create(Obra obra) {

        AreaCientifica areaCientifica = obra.getAreaCientifica();
        Estado estado = obra.getEstado();
        Idioma idioma = obra.getIdioma();
        TipoObra tipoObra = obra.getTipoObra();

        if (areaCientifica == null || estado == null || idioma == null || tipoObra == null) {
            throw new RuntimeException("Entidades associadas à obra não informadas");
        }

        // Salve a obra
        return obraRepository.save(obra);
    }

    /**
     *
     * @param id
     * @return
     */
    public Optional<Obra> findById(Long id) {
        return obraRepository.findById(id);
    }

    /**
     *
     * @return
     */
    public List<Obra> findAll() {
        return obraRepository.findAll();
    }

    /**
     *
     * @param autor
     * @return
     */
    public List<Obra> findByAutor(String autor) {
        return obraRepository.findByAutor1ContainingIgnoreCaseOrAutor2ContainingIgnoreCaseOrAutor3ContainingIgnoreCase(
                autor, autor, autor);
    }

    /**
     *
     * @param areacientifica
     * @return
     */
    public List<Obra> findByAreaCientifica(String areacientifica) {
        return obraRepository.findByAreaCientifica(areacientifica);
    }

    /**
     *
     * @param obra
     * @return
     */
    @Transactional
    public Obra update(Obra obra) {

        return obraRepository.save(obra);
    }

    /**
     *
     * @param id
     */
    @Transactional
    public void delete(Long id) {
        // Verifique se a obra possui empréstimos, reservas ou multas relacionadas antes
        // de excluir
        var obra = obraRepository.findById(id).get();

        List<Emprestimo> emprestimos = obra.getEmprestimoList();
        List<Reserva> reservas = obra.getReservaList();
        List<Movimento> movimentos = obra.getMovimentoList();
        List<Multa> multas = obra.getEstado().getMultaList();

        if (!emprestimos.isEmpty() || !reservas.isEmpty() || !movimentos.isEmpty()
                || multas.stream().anyMatch(multa -> multa.getEmprestimo().getObra().equals(id))) {
            throw new RuntimeException("Obra possui entidades relacionadas e não pode ser excluída");
        }

        obraRepository.deleteById(id);
    }

    /**
     *
     * @param idioma
     * @return
     */
    public List<Obra> findByIdioma(String idioma) {
        return obraRepository.findByIdioma(idioma);
    }

    /**
     *
     * @param titulo
     * @return
     */
    public List<Obra> findByTitulo(String titulo) {
        return obraRepository.findByTitulo(titulo);
    }

}
