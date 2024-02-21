package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Multa;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.repository.EstadoRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class EstadoService {

    private final EstadoRepository estadoRepository;

    // CRUD methods

    @Transactional
    public Estado create(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Optional<Estado> findById(Long id) {
        return estadoRepository.findById(id);
    }

    public List<Estado> findAll() {
        return estadoRepository.findAll();
    }

    @Transactional
    public Estado update(Estado estado) {
        return estadoRepository.save(estado);
    }

    @Transactional
    public void delete(Long id) {
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

    public List<Emprestimo> findEmprestimosPorEstado(Estado estado) {
        return estado.getEmprestimoList();
    }

    public List<Multa> findMultasPorEstado(Estado estado) {
        return estado.getMultaList();
    }

    public List<Obra> findObrasPorEstado(Estado estado) {
        return estado.getObraList();
    }

    public List<Reserva> findReservasPorEstado(Estado estado) {
        return estado.getReservaList();
    }

    public Estado findByDescricao(String estado) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByDescricao'");
    }

}
