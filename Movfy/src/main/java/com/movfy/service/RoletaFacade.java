package com.movfy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movfy.model.Filme;
import com.movfy.strategy.RoletaStrategy;

@Service
public class RoletaFacade {

    @Autowired
    private TmdbService tmdbService;

    @Autowired
    private RoletaStrategy roletaOscarStrategy;

    public Filme girarRoletaOscar() {
        Long idSorteado = roletaOscarStrategy.sortearFilmeId();
        return tmdbService.buscarFilmePorId(idSorteado);
    }
}