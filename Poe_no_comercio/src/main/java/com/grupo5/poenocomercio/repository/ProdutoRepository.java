package com.grupo5.poenocomercio.repository;

import com.grupo5.poenocomercio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
