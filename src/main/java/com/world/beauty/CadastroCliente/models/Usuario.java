package com.world.beauty.CadastroCliente.models;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity(name = "usuario")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor @NoArgsConstructor @Data @ToString
public abstract class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String dataNascimento;

    @Column(nullable = false)
    private String genero;

    @OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "id_usuario")
    private Set<Contato> contatos = new HashSet<Contato>();

    @OneToMany(mappedBy = "cliente",cascade = CascadeType.ALL)
    private Set<Pedido> pedidos;
    
    public Usuario( String nome, String dataNascimento, String genero) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.genero = genero;
    }
    
    public int getIdade(){
        Calendar cal = Calendar.getInstance();
        Integer anoAtual = cal.get(Calendar.YEAR);
        String[] anoNascArray=this.getDataNascimento().split("/");
        Integer anoNascimento = Integer.parseInt(anoNascArray[2]);
        Integer idade = anoAtual-anoNascimento;
        return idade;
    }
}
