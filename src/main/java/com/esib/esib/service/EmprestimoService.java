package com.esib.esib.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esib.esib.modelo.Emprestimo;
import com.esib.esib.modelo.Obra;
import com.esib.esib.repository.EmprestimoRepository;
import com.esib.esib.repository.ObraRepository;

@Service
public class EmprestimoService {

    @Autowired
    private EmprestimoRepository emprestimoRepository;
    @Autowired
    private ObraRepository obraRepository;

    // CRUD methods

    @Transactional
    public Emprestimo criarEmprestimo(Emprestimo emprestimo) {
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

    public Optional<Emprestimo> buscarEmprestimoPorId(Long id) {
        return emprestimoRepository.findById(id);
    }

    public List<Emprestimo> buscarTodosEmprestimos() {
        return emprestimoRepository.findAll();
    }

    public List<Emprestimo> buscarEmprestimosPorBibliotecario(Long bibliotecario) {
        return emprestimoRepository.findByBibliotecario(bibliotecario);
    }

    public List<Emprestimo> buscarEmprestimosPorUtilizador(Long utilizador) {
        return emprestimoRepository.findByUtilizador(utilizador);
    }

    public List<Emprestimo> buscarEmprestimosPorObra(Long obra) {
        return emprestimoRepository.findByObra(obra);
    }

    public List<Emprestimo> buscarEmprestimosPorEstado(String estado) {
        return emprestimoRepository.findByEstado(estado);
    }

    @Transactional
    public Emprestimo atualizarEmprestimo(Emprestimo emprestimo) {
        return emprestimoRepository.save(emprestimo);
    }
    /*
     * @Transactional
     * public void excluirEmprestimo(Long id) {
     * Optional<Emprestimo> emprestimo = emprestimoRepository.findById(id);
     * // Verifique se o emprestimo possui devoluções antes de excluir
     * List<Devolucao> devolucoes = emprestimo.getDevolucaoList();
     * if (!devolucoes.isEmpty()) {
     * throw new
     * RuntimeException("Emprestimo possui devoluções associadas e não pode ser excluído"
     * );
     * }
     * 
     * emprestimoRepository.deleteById(id);
     * }
     */

    // Method to calculate due date
    private Date calcularDataParaDevolucao(int prazoEmprestimo) {
        long milisegundosPorDia = 24 * 60 * 60 * 1000;
        var dataEmprestimo = new Date();
        return new Date(dataEmprestimo.getTime() + prazoEmprestimo * milisegundosPorDia);
    }

}
