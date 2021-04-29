package com.world.beauty.CadastroCliente.repository;


import java.util.List;

import com.world.beauty.CadastroCliente.models.Pedido;
import com.world.beauty.CadastroCliente.models.PedidoServiceCount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    @Query(value = "select descricao,count(descricao) as qtdServicos from pedido where tipo='servico' group by descricao order by descricao", nativeQuery = true)
    List<PedidoServiceCount> findDescricaoByServico();

    @Query(value = "select p.descricao,count(p.descricao) as qtdServicos from pedido as p inner join usuario as u on u.id=p.id_usuario where p.tipo='servico' and u.genero=:genero group by p.descricao order by p.descricao", nativeQuery = true)
    List<PedidoServiceCount> findDescricaoByServicoAndGenero(@Param("genero") String genero);
    
}
