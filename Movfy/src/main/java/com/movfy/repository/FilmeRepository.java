package com.movfy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.movfy.model.Filme;

public interface FilmeRepository extends JpaRepository<Filme, Long> {
}