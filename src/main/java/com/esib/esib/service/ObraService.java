package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

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

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class ObraService {

    private final ObraRepository obraRepository;

    // CRUD methods

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

    public Optional<Obra> findById(Long id) {
        return obraRepository.findById(id);
    }

    public List<Obra> findAll() {
        return obraRepository.findAll();
    }

    public List<Obra> findByAutor(String autor) {
        return obraRepository.findByAutor1ContainingIgnoreCaseOrAutor2ContainingIgnoreCaseOrAutor3ContainingIgnoreCase(
                autor, autor, autor);
    }

    public List<Obra> findByAreaCientifica(String areacientifica) {
        return obraRepository.findByAreaCientifica(areacientifica);
    }

    @Transactional
    public Obra update(Obra obra) {

        return obraRepository.save(obra);
    }

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

    public List<Obra> findByIdioma(String idioma) {
        return obraRepository.findByIdioma(idioma);
    }

    public List<Obra> findByTitulo(String titulo) {
        return obraRepository.findByTitulo(titulo);
    }

    // Methods related
}