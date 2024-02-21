package com.esib.esib.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.EmprestimoRepository;
import com.esib.esib.repository.ObraRepository;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Data
public class EmprestimoService {

    private final EmprestimoRepository emprestimoRepository;
    private final ObraRepository obraRepository;

    // CRUD methods

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
        emprestimo.setDataParaDevolucao(calcularDataParaDevolucao(4320));
        emprestimo.setAtraso(0);

        // Atualize a quantidade disponível da obra
        obra.setDisponivel(false);
        obraRepository.save(obra);

        // Salve o emprestimo
        emprestimoRepository.save(emprestimo);

        return emprestimo;
    }

    public Optional<Emprestimo> findById(Long id) {
      return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> findAll() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> findByBibliotecario(Long bibliotecario) {
        return emprestimoRepository.findByBibliotecario(bibliotecario);
    }

    public List<Emprestimo> findByUtilizador(Long utilizador) {
        return emprestimoRepository.findByUtilizador(utilizador);
    }

    public List<Emprestimo> findByObra(Long obra) {
        return emprestimoRepository.findByObra(obra);
    }
    
    public List<Emprestimo> findByTitulo(String titulo) {
        return emprestimoRepository.findByTitulo(titulo);
    }

    public List<Emprestimo> findByIdioma(String idioma) {
        return emprestimoRepository.findByIdioma(idioma);
    }

    public List<Emprestimo> findByAreaCientifica(String estado) {
        return emprestimoRepository.findByAcientifica(estado);
    }

    @Transactional
    public Emprestimo update(Emprestimo emprestimo) {
        
        Emprestimo newEmprestimo = new Emprestimo();

        newEmprestimo.setId(emprestimo.getId());
        newEmprestimo.setBibliotecario(emprestimo.getBibliotecario());
        newEmprestimo.setAtraso(emprestimo.getAtraso());
        newEmprestimo.setDataEmprestimo(emprestimo.getDataEmprestimo());
        newEmprestimo.setDataParaDevolucao(emprestimo.getDataParaDevolucao());
        newEmprestimo.setEstado(emprestimo.getEstado());

        return emprestimoRepository.save(newEmprestimo);
    }

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
    private Date calcularDataParaDevolucao(int prazoEmprestimo) {
        long milisegundosPorDia = 24 * 60 * 60 * 1000;
        var dataEmprestimo = new Date();
        return new Date(dataEmprestimo.getTime() + prazoEmprestimo * milisegundosPorDia);
    }

    public List<Emprestimo> findByEstado(String estado) {
        return emprestimoRepository.findByEstado(estado);
    }


}
