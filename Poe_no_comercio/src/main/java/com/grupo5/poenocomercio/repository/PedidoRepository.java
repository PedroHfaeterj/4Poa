package com.grupo5.poenocomercio.repository;

import com.grupo5.poenocomercio.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}