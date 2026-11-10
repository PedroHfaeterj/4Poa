package com.movfy.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movfy.model.Filme;

import tools.jackson.databind.JsonNode;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    // MÉTODO ANTIGO (Usado pela Roleta e para salvar filmes)
    public Filme buscarFilmePorId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/movie/" + id + "?language=pt-BR&api_key=" + apiKey;
        
        try {
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);
            if (root != null) {
                Filme filme = new Filme();
                filme.setId(root.path("id").asLong());
                filme.setTitulo(root.path("title").asText());
                filme.setSinopse(root.path("overview").asText());
                
                String poster = root.path("poster_path").asText();
                if (!poster.equals("null") && !poster.isEmpty()) {
                    filme.setUrlCapa("https://image.tmdb.org/t/p/w500" + poster);
                }
                return filme;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar filme por ID: " + e.getMessage());
        }
        return null;
    }

    // MÉTODO NOVO (Usado pela barra de pesquisa do Diário)
    public List<Filme> buscarFilmesPorTitulo(String titulo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/search/movie?query=" + titulo + "&language=pt-BR&api_key=" + apiKey;
        
        List<Filme> lista = new ArrayList<>();
        try {
            JsonNode root = restTemplate.getForObject(url, JsonNode.class);
            JsonNode results = root.path("results");
            
            for (JsonNode node : results) {
                Filme filme = new Filme();
                filme.setId(node.path("id").asLong());
                filme.setTitulo(node.path("title").asText());
                
                String poster = node.path("poster_path").asText();
                if (!poster.equals("null") && !poster.isEmpty()) {
                    filme.setUrlCapa("https://image.tmdb.org/t/p/w500" + poster);
                } else {
                    filme.setUrlCapa("https://via.placeholder.com/500x750?text=Sem+Capa"); 
                }
                
                lista.add(filme);
                if (lista.size() >= 5) break; 
            }
        } catch (Exception e) {
            System.err.println("Erro na busca por nome: " + e.getMessage());
        }
        return lista;
    }
}