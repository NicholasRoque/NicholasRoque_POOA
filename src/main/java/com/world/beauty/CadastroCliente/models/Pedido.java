package com.world.beauty.CadastroCliente.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "pedido")
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idPedido;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Float preco;

    @Column(nullable = false)
    private String data;
    
    @ManyToOne
    @JoinColumn(name="id_usuario")
    private Cliente cliente;

    public Pedido(String descricao,String tipo,Float preco,String data) {
        this.descricao = descricao;
        this.tipo = tipo;
        this.preco = preco;
        this.data = data;
    }

}
