package br.com.treinaweb.twprojetos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.treinaweb.twprojetos.entidades.Projeto;

public interface ProjetoRepository extends JpaRepository<Projeto,Long>{
    
}
