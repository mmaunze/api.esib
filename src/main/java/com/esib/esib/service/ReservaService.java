package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Estado;
import com.esib.esib.modelo.Obra;
import com.esib.esib.modelo.Reserva;
import com.esib.esib.modelo.Utilizador;
import com.esib.esib.repository.ReservaRepository;

@Service
public class ReservaService {

    @Autowired
    private ReservaRepository reservaRepository;

    // CRUD methods

    @Transactional
    public Reserva criarReserva(Reserva reserva) {
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

    public Optional<Reserva> buscarReservaPorId(Long id) {
        return reservaRepository.findById(id);
    }

    public List<Reserva> buscarTodasReservas() {
        return reservaRepository.findAll();
    }

    public List<Reserva> buscarReservasPorObra(Long obra) {
        return reservaRepository.findByObra(obra);
    }

    public List<Reserva> buscarReservasPorUtilizador(Long utilizador) {
        return reservaRepository.findByUtilizador(utilizador);
    }

    @Transactional
    public Reserva atualizarReserva(Reserva reserva) {
        // Verifique se a obra associada existe
        Obra obra = reserva.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada à reserva não informada");
        }

        // Atualize a reserva
        return reservaRepository.save(reserva);
    }

    @Transactional
    public void excluirReserva(Long id) {
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

    public Obra buscarObraPorReserva(Reserva reserva) {
        return reserva.getObra();
    }

    public Utilizador buscarUtilizadorPorReserva(Reserva reserva) {
        return reserva.getUtilizador();
    }

    public Estado buscarEstadoPorReserva(Reserva reserva) {
        return reserva.getEstado();
    }

    // Additional methods
    /*
     * public boolean verificarDisponibilidadeObra(Obra obra) {
     * return reservaRepository.findByObraAndIdEstado(obra, new
     * Estado().isAciva()).isEmpty(); // Estado da reserva = Ativa
     * }
     */
}
