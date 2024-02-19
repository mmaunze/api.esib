package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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

@Service
public class ObraService {

    @Autowired
    private ObraRepository obraRepository;

    // CRUD methods

    @Transactional
    public Obra criarObra(Obra obra) {
        /*
         * // Verifique se a localização da obra já está ocupada
         * String localizacao = obra.getLocalizacao();
         * if (obraRepository.existsByLocalizacao(localizacao)) {
         * throw new RuntimeException("Localização da obra já ocupada");
         * }
         */
        // Verifique se as entidades associadas existem
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

    public Optional<Obra> buscarObraPorId(Long id) {
        return obraRepository.findById(id);
    }

    public List<Obra> buscarTodasObras() {
        return obraRepository.findAll();
    }

    public List<Obra> buscarObrasPorTitulo(String titulo) {
        return obraRepository.findByTituloContainingIgnoreCase(titulo);
    }

    public List<Obra> buscarObrasPorAutor(String autor) {
        return obraRepository.findByAutor1ContainingIgnoreCaseOrAutor2ContainingIgnoreCaseOrAutor3ContainingIgnoreCase(
                autor, autor, autor);
    }

    public List<Obra> buscarObrasPorAreaCientifica(AreaCientifica areaCientifica) {
        return obraRepository.findByAreaCientifica(areaCientifica.getDescricao());
    }

    /*
     * @Transactional
     * public Obra atualizarObra(Obra obra) {
     * // Verifique se a localização da obra já está ocupada (se alterada)
     * if
     * (!obra.getLocalizacao().equals(obraRepository.findById(obra.getIdObra()).get(
     * ).getLocalizacao())) {
     * if (obraRepository.existsByLocalizacao(obra.getLocalizacao())) {
     * throw new RuntimeException("Localização da obra já ocupada");
     * }
     * }
     * 
     * return obraRepository.save(obra);
     * }
     */

    @Transactional
    public void excluirObra(Long id) {
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

    // Methods related
}