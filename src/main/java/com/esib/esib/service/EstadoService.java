package com.esib.esib.service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.repository.EstadoRepository;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    // CRUD methods

    @Transactional
    public Estado criarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Optional<Estado> buscarEstadoPorId(Long id) {
        return estadoRepository.findById(id);
    }

    public List<Estado> buscarTodosEstados() {
        return estadoRepository.findAll();
    }

    @Transactional
    public Estado atualizarEstado(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void excluirEstado(Long id) {
        var estado = estadoRepository.getById(id);
        // Verifique se o estado possui entidades relacionadas antes de excluir
        List<Emprestimo> emprestimos = estado.getEmprestimoList();
        List<Multa> multas = estado.getMultaList();
        List<Obra> obras = estado.getObraList();
        List<Reserva> reservas = estado.getReservaList();

        if (!emprestimos.isEmpty() || !multas.isEmpty() || !obras.isEmpty() || !reservas.isEmpty()) {
            throw new RuntimeException("Estado possui entidades relacionadas e não pode ser excluído");
        }

        estadoRepository.deleteById(id);
    }

    // Methods related to relationships

    public List<Emprestimo> buscarEmprestimosPorEstado(Estado estado) {
        return estado.getEmprestimoList();
    }

    public List<Multa> buscarMultasPorEstado(Estado estado) {
        return estado.getMultaList();
    }

    public List<Obra> buscarObrasPorEstado(Estado estado) {
        return estado.getObraList();
    }

    public List<Reserva> buscarReservasPorEstado(Estado estado) {
        return estado.getReservaList();
    }

}
