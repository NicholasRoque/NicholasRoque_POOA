package com.world.beauty.CadastroCliente.models;
import javax.persistence.Entity;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity(name = "cliente")
@Data @NoArgsConstructor @EqualsAndHashCode(callSuper=false)
public class Cliente extends Usuario {

    public Cliente( String nome, String dataNascimento, String genero) {
       super(nome, dataNascimento, genero);
    }

    
}
