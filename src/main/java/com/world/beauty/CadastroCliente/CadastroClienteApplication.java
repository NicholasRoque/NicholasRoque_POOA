package com.world.beauty.CadastroCliente;

import com.world.beauty.CadastroCliente.models.Cliente;
import com.world.beauty.CadastroCliente.models.Contato;
import com.world.beauty.CadastroCliente.models.Pedido;
import com.world.beauty.CadastroCliente.repository.ClienteRepository;
import com.world.beauty.CadastroCliente.repository.PedidoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CadastroClienteApplication implements CommandLineRunner {

	@Autowired
	private ClienteRepository cRepository;

	@Autowired
	private PedidoRepository pRepository;
		   
	public static void main(String[] args) {
		SpringApplication.run(CadastroClienteApplication.class, args);
		
	}

	@Override
	public void run(String... args) throws Exception {

		//-------------------------------------------------------------------------------------
		Pedido pedido = new Pedido("teste","servico",20.89f,"21/10/2020");
		Contato contato = new Contato("(12) 99742-4087");
		Cliente cliente = new Cliente("Nicholas","15/04/2002","masculino");

		cliente.getContatos().add(contato);
		cRepository.save(cliente);

		pedido.setCliente(cliente);
		pRepository.save(pedido);
		//-------------------------------------------------------------------------------------
		Pedido pedido2 = new Pedido("teste","servico",20.89f,"21/10/2020");
		Contato contato2 = new Contato("(12) 94525-3187");
		Cliente cliente2 = new Cliente("Maria","02/09/1999","feminino");

		cliente2.getContatos().add(contato2);
		cRepository.save(cliente2);
		
		pedido2.setCliente(cliente2);
		pRepository.save(pedido2);


	}
}
