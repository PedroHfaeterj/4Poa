package com.movfy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.movfy.model.Filme;
import com.movfy.repository.FilmeRepository;
import com.movfy.service.TmdbService;
import com.movfy.service.IaService;

@RestController
@RequestMapping("/api/diario")
@CrossOrigin(origins = "*")
public class DiarioController {

    @Autowired private FilmeRepository repo;
    @Autowired private TmdbService tmdb;
    @Autowired private IaService iaService;

    @GetMapping
    public List<Filme> listarVistos() {
        return repo.findAll();
    }

    @PostMapping("/{idTmdb}/{nota}")
    public Filme adicionarFilme(@PathVariable Long idTmdb, @PathVariable Integer nota) {
        Filme filme = tmdb.buscarFilmePorId(idTmdb);
        if (filme != null) {
            filme.setNota(nota);
            return repo.save(filme);
        }
        return null;
    }

   
    
    @GetMapping("/buscar")
public List<Filme> buscarFilmePorNome(@RequestParam String nome) {
    return tmdb.buscarFilmesPorTitulo(nome);
}
    
    
    @PostMapping("/chat")
    @SuppressWarnings("unchecked")
    public String conversarComIa(@RequestBody java.util.Map<String, Object> payload) {
        String mensagem = (String) payload.get("mensagem");
        List<String> filmes = (List<String>) payload.get("filmes");
        return iaService.conversarNoChat(mensagem, filmes);
    }
}