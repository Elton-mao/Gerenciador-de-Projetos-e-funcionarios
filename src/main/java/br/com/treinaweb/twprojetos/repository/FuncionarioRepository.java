package br.com.treinaweb.twprojetos.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import br.com.treinaweb.twprojetos.entidades.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario ,Long>{
    
    @EntityGraph(attributePaths = {"endereco","cargo"})
    List<Funcionario> findAll();

  //  @Query("select f from Funcinario f where f.cargo.nome= :cargoNome")
     //List<Funcionario> buscarPorCargo(String cargoNome);
}
