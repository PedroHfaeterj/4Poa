package com.grupo5.poenocomercio.repository;

import com.grupo5.poenocomercio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {
}