package com.senac.FixIt.repository;

import com.senac.FixIt.models.Chamado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChamadoRepository extends JpaRepository<Chamado, Integer> {

    // Buscar chamados por status
    List<Chamado> findByStatus(String status);

    // Buscar chamados por prioridade
    List<Chamado> findByPrioridade(String prioridade);

    // Buscar chamados por departamento
    List<Chamado> findByDepartamento(String departamento);

    // Buscar por título ou descrição contendo um texto

    // Combinações: por status e prioridade
    List<Chamado> findByStatusAndPrioridade(String status, String prioridade);

    // 🔍 Filtro por texto
    List<Chamado> findByTituloContainingIgnoreCaseOrDescricaoContainingIgnoreCase(String titulo, String descricao);

    // 🔍 Filtros combinados dinâmicos (status, prioridade, departamento)
    @Query("SELECT c FROM Chamado c " +
           "WHERE (:status IS NULL OR c.status = :status) " +
           "AND (:prioridade IS NULL OR c.prioridade = :prioridade) " +
           "AND (:departamento IS NULL OR c.departamento = :departamento)")
    List<Chamado> filtrarChamados(String status, String prioridade, String departamento);
    
}
