package com.world.beauty.CadastroCliente.controllers;

import com.world.beauty.CadastroCliente.models.Cliente;
import com.world.beauty.CadastroCliente.models.Contato;
import com.world.beauty.CadastroCliente.models.Pedido;
import com.world.beauty.CadastroCliente.repository.ClienteRepository;
import com.world.beauty.CadastroCliente.repository.PedidoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/cliente")
@Api("API de cliente")
public class ClienteController {
    
    @Autowired 
    ClienteRepository cRepository;

    @Autowired
	private PedidoRepository pRepository;
    
    @PostMapping("/cadastrar")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView createCliente(Cliente cliente, Contato contato){
        ModelAndView mv = new ModelAndView("listar");
        cliente.getContatos().add(contato);
        cRepository.save(cliente);
        mv = getCliente(Optional.of("todos"));
        return mv;
    }

    @GetMapping("/cadastrar")
    @ResponseStatus(HttpStatus.OK)
    public String getCadastrarCliente(){
        return "cadastrar";
    }

    @GetMapping("/listar")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getCliente(Optional<String> opt){
        List<Cliente> clientes;        
        ModelAndView mv = new ModelAndView("listar");

        if(opt.isEmpty()||opt.get().equals("todos")){
            clientes = cRepository.findAll();
            mv.addObject("opt", "todos");

        }else{
            clientes = cRepository.findByGeneroOrderByNome(opt.get());
            mv.addObject("opt", opt.get());
        }
        mv.addObject("clientes", clientes);

        clientes.forEach((c)->{
            System.out.println(c.toString());
        });

        return mv;
    }

    @GetMapping("/delete")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView deleteClienteById(Long id){
        ModelAndView mv = new ModelAndView("listar");

        cRepository.deleteById(id);
        mv.addObject("clientes", cRepository.findAll());
        mv.addObject("opt", "todos");

        return mv;
    }

    @GetMapping("/edit")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView editCliente( Long id){
        ModelAndView mv = new ModelAndView("edit");
        Optional<Cliente> cliente = cRepository.findById(id);
        cliente.ifPresent(c->mv.addObject("cliente", c));
        return mv;
    }

    @PostMapping("/update")
    @ResponseStatus(HttpStatus.CREATED)
    public ModelAndView updateCliente(Cliente cliente,Contato contato){
        ModelAndView mv = new ModelAndView("edit");
        cRepository.findById(cliente.getId()).ifPresent((c)->{
            cliente.getContatos().add(contato);
            cRepository.save(cliente);
        });

           mv.addObject("cliente", cliente);
        return mv;
    }

    @GetMapping("/pedidos")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getPedidos(@RequestParam Long id){
        ModelAndView mv = new ModelAndView("pedidos");
        cRepository.findById(id).ifPresent((c)->{
            mv.addObject("pedidos", c.getPedidos());
            mv.addObject("id", id);
        });

        return mv;
    }

    @PostMapping("/pedidos")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView postPedido(Pedido pedido,@RequestParam Long id){
        ModelAndView mv = new ModelAndView("pedidos");
        System.out.println("pedidos: "+pedido.toString());
        cRepository.findById(id).ifPresent((c)->{
                pedido.setCliente(c);
                pRepository.save(pedido);
                System.out.println("pedidos: "+c.getPedidos());
                mv.addObject("pedidos", c.getPedidos());
        });
        mv.addObject("id", id);
        return mv;
    }
}
