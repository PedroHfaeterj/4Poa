package com.grupo5.poenocomercio.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    
    @ManyToOne
    private Cliente cliente;

    
    @ManyToMany
    private List<Produto> produtos;

    private Double valorTotal;
}