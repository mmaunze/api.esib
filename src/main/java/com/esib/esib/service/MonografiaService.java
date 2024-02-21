package com.esib.esib.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Monografia;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.MonografiaRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class MonografiaService {

    private final MonografiaRepository monografiaRepository;

    // CRUD methods

    @Transactional
    public Monografia create(Monografia monografia) {
        // Verifique se a obra associada existe
        Obra obra = monografia.getObra();
        if (obra == null) {
            throw new RuntimeException("Obra associada à monografia não informada");
        }

        // Verifique se o ISBN da obra já está associado a outra monografia
        if (obra.getMonografia() != null) {
            throw new RuntimeException("Obra já está associada a outra monografia");
        }

        // Associe a monografia à obra
        obra.setMonografia(monografia);

        return monografiaRepository.save(monografia);
    }

    public Optional<Monografia> findById(Long id) {
        return monografiaRepository.findById(id);
    }

    public List<Monografia> findAll() {
        return monografiaRepository.findAll();
    }

    @Transactional
    public Monografia update(Monografia monografia) {
        return monografiaRepository.save(monografia);
    }

    @Transactional
    public void delete(Long id) {
        var monografia = monografiaRepository.findById(id);

        /*
         * // Verifique se a monografia possui empréstimos antes de excluir
         * Obra obra = monografia.getIdObra();
         * if (obra != null && obra.getEmprestimoList() != null &&
         * !obra.getEmprestimoList().isEmpty()) {
         * throw new
         * RuntimeException("Monografia possui empréstimos associados e não pode ser excluída"
         * );
         * }
         */
        monografiaRepository.deleteById(id);
    }

    // Methods related to relationships

    public Obra findObraPorMonografia(Monografia monografia) {
        return monografia.getObra();
    }

    public List<Monografia> findByTitulo(String titulo) {

        return monografiaRepository.findByTitulo(titulo);
    }

    public List<Monografia> findByIdioma(String idioma) {
        return monografiaRepository.findByIdioma(idioma);
    }

    public List<Monografia> findByAreaCientifica(String areacientifica) {

        return monografiaRepository.findByAreaCientifica(areacientifica);

    }

    public List<Monografia> findByFaculdade(String faculdade) {
        return monografiaRepository.findByFaculdade(faculdade);
    }

    public List<Monografia> findByCurso(String curso) {
        return monografiaRepository.findByCurso(curso);
    }

    public List<Monografia> findBySupervisor(String supervisor) {
        return monografiaRepository.findBySupervisor(supervisor);
    }

}
