package com.grupo5.poenocomercio.controller;

import com.grupo5.poenocomercio.model.Cliente;
import com.grupo5.poenocomercio.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @GetMapping
    public List<Cliente> listarClientes() {
        return repository.findAll();
    }

    @PostMapping
    public Cliente cadastrarCliente(@RequestBody Cliente cliente) {
        return repository.save(cliente);
    }
}