package com.world.beauty.CadastroCliente.controllers;

import com.world.beauty.CadastroCliente.models.Cliente;
import com.world.beauty.CadastroCliente.repository.ClienteRepository;
import com.world.beauty.CadastroCliente.repository.PedidoRepository;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import io.swagger.annotations.Api;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;

@Controller
@RequestMapping("/relatorio")
@Api("API de relatorio")
public class RelatorioController {
    
    @Autowired 
    ClienteRepository cRepository;

    @Autowired 
    PedidoRepository vRepository;

    @GetMapping("/idade")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getRelatorioIdade(){
        Float mediaMasculino = 0.0f;
        Float mediaFeminino = 0.0f;
        Float mediaTotal = 0.0f;
        ModelAndView mv = new ModelAndView("idadeMedia");
        List<Cliente> clientesMasculino = cRepository.findByGeneroOrderByNome("masculino");
        List<Cliente> clientesFeminino = cRepository.findByGeneroOrderByNome("feminino");
        
        mediaMasculino = getMedia(clientesMasculino);
        mediaFeminino = getMedia(clientesFeminino);
        mediaTotal = (mediaMasculino+mediaFeminino)/2;
        
        mv.addObject("mediaMasculino", mediaMasculino);
        mv.addObject("mediaFeminino", mediaFeminino);
        mv.addObject("mediaTotal", mediaTotal);
        return mv;
    }

    @GetMapping("/servico")
    @ResponseStatus(HttpStatus.OK)
    public ModelAndView getRelatorioServicos(Optional<String> opt){
        ModelAndView mv = new ModelAndView("servicos");
        if(opt.isEmpty()||opt.get().equals("todos")){
            mv.addObject("servicos",vRepository.findDescricaoByServico());
            mv.addObject("opt","todos");

        }else{
            mv.addObject("servicos",vRepository.findDescricaoByServicoAndGenero(opt.get()));
            mv.addObject("opt",opt.get());

        }

        return mv;
    }

    private float getMedia(List<Cliente> clientes){
        Float media = 0.0f;
        for(Cliente c : clientes){
            media+=c.getIdade();
        }
        return media = media/clientes.size();
    }
}
