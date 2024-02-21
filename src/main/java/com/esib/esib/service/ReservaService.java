package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.ReservaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class ReservaService {

    private final ReservaRepository reservaRepository;

    // CRUD methods

    @Transactional
    public Reserva create(Reserva reserva) {
        // Verifique se a obra associada existe
        Obra obra = reserva.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada à reserva não informada");
        }

        // Verifique se a obra está disponível para reserva
        if (!obra.getDisponivel()) {
            throw new RuntimeException("Obra indisponível para reserva");
        }
        /*
         * // Verifique se o utilizador já possui uma reserva ativa para a mesma obra
         * Utilizador utilizador = reserva.getutilizador();
         * List<Reserva> reservas =
         * reservaRepository.findByUtilizadorAndIdObraAndIdEstado(utilizador, obra, new
         * Estado().isAciva()); // Estado da reserva = Ativa
         * if (!reservas.isEmpty()) {
         * throw new
         * RuntimeException("Utilizador já possui uma reserva ativa para esta obra");
         * }
         */

        // Reserve a obra (atualize o estado da obra para "Reservada")
        obra.setDisponivel(false);

        // Salve a reserva
        return reservaRepository.save(reserva);
    }

    public Optional<Reserva> findById(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> findAll() {
        return reservaRepository.findAll();
    }

    @Transactional
    public Reserva update(Reserva reserva) {
        // Verifique se a obra associada existe
        Obra obra = reserva.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada à reserva não informada");
        }

        // Atualize a reserva
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void delete(Long id) {
        // Verifique se a reserva está ativa antes de excluir
        var reserva = reservaRepository.findById(id).get();
        if (reserva.getEstado().getId() != 1) { // Estado da reserva != Ativa
            throw new RuntimeException("Não é possível excluir reserva inativa");
        }

        // Libere a obra reservada (atualize o estado da obra para "Disponível")
        reserva.getObra().setDisponivel(true);

        reservaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Obra findObraPorReserva(Reserva reserva) {
        return reserva.getObra();
    }

    public Utilizador findUtilizadorPorReserva(Reserva reserva) {
        return reserva.getUtilizador();
    }

    public Estado findEstadoPorReserva(Reserva reserva) {
        return reserva.getEstado();
    }

    public List<Reserva> findByUtilizador(Long utilizador) {
        return reservaRepository.findByUtilizador(utilizador);
    }

    public List<Reserva> findByObra(Long obra) {
        return reservaRepository.findByObra(obra);
    }

    public List<Reserva> findEmprestimosPorEstado(String estado) {
        return reservaRepository.findByEstado(estado);
    }

    public List<Reserva> findByTitulo(String estado) {
        return reservaRepository.findByTitulo(estado);
    }

    public List<Reserva> findByIdioma(String estado) {
        return reservaRepository.findByIdioma(estado);
    }

    public List<Reserva> findByAreaCientifica(String estado) {
        return reservaRepository.findByAcientifica(estado);
    }

    // Additional methods
    /*
     * public boolean verificarDisponibilidadeObra(Obra obra) {
     * return reservaRepository.findByObraAndIdEstado(obra, new
     * Estado().isAciva()).isEmpty(); // Estado da reserva = Ativa
     * }
     */
}
