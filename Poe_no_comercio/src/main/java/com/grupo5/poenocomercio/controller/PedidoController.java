package com.grupo5.poenocomercio.controller;

import com.grupo5.poenocomercio.model.Pedido;
import com.grupo5.poenocomercio.model.Produto;
import com.grupo5.poenocomercio.repository.PedidoRepository;
import com.grupo5.poenocomercio.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ProdutoRepository produtoRepository; 

    @GetMapping
    public List<Pedido> listarPedidos() {
        return pedidoRepository.findAll();
    }

    @PostMapping
    public Pedido cadastrarPedido(@RequestBody Pedido pedido) {
        double valorTotal = 0.0;
        
        
        if (pedido.getProdutos() != null) {
            for (Produto produtoEnviado : pedido.getProdutos()) {
                
                Produto produtoReal = produtoRepository.findById(produtoEnviado.getId()).orElse(null);
                
                
                if (produtoReal != null) {
                    valorTotal += produtoReal.getPreco();
                }
            }
        }
        
       
        pedido.setValorTotal(valorTotal);
        return pedidoRepository.save(pedido);
    }
}