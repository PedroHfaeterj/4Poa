package com.movfy.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movfy.model.Filme;
import com.movfy.repository.FilmeRepository;
import com.movfy.strategy.RoletaStrategy;

@Service
public class RoletaFacade {

    @Autowired
    private TmdbService tmdbService;
    
    @Autowired
    private FilmeRepository filmeRepository;

    @Autowired
    private RoletaStrategy roletaOscarStrategy;
    
    
    @Autowired
    private IaService iaService;

    public Filme girarRoletaOscar() {
        Long idSorteado = roletaOscarStrategy.sortearFilmeId();
        Filme filme = tmdbService.buscarFilmePorId(idSorteado);
        
        if (filme != null) {
            
            String comentarioGerado = iaService.gerarComentario(filme.getTitulo());
            filme.setComentarioIa(comentarioGerado);
            
            filmeRepository.save(filme);
        }
        
        return filme;
    }
}