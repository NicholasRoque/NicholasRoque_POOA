package com.world.beauty.CadastroCliente.repository;

import java.util.List;

import com.world.beauty.CadastroCliente.models.Cliente;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    
    @Query(value = "select * from usuario as u inner join cliente as c on c.id=u.id where u.genero=:genero order by u.nome", nativeQuery = true)
    List<Cliente> findByGeneroOrderByNome(@Param("genero") String genero);
    
    @Override
    @Query(value = "select * from usuario as u inner join cliente as c on c.id=u.id order by nome", nativeQuery = true)
    List<Cliente> findAll();

}
