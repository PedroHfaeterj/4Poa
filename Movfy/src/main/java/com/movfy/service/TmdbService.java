package com.movfy.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.movfy.model.Filme;

@Service
public class TmdbService {

    @Value("${tmdb.api.key}")
    private String apiKey;

    @Value("${tmdb.api.url}")
    private String apiUrl;

    public Filme buscarFilmePorId(Long id) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/movie/" + id + "?language=pt-BR&api_key=" + apiKey;
        
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> root = restTemplate.getForObject(url, Map.class);
            
            if (root != null && root.containsKey("id")) {
                Filme filme = new Filme();
                filme.setId(Long.valueOf(root.get("id").toString()));
                filme.setTitulo((String) root.get("title"));
                filme.setSinopse((String) root.get("overview"));
                
                // Extrai os 4 dígitos do ano de lançamento (ex: "2023-07-19" -> "2023")
                Object dataObj = root.get("release_date");
                String ano = "Clássico";
                if (dataObj != null && dataObj.toString().length() >= 4) {
                    ano = dataObj.toString().substring(0, 4);
                }
                
                // Guarda a faixa de premiação reaproveitando o campo comentarioIa
                filme.setComentarioIa("🎬 Lançamento: " + ano + "  •  🏆 Vencedor do Oscar de Melhor Filme");
                
                Object posterObj = root.get("poster_path");
                if (posterObj != null) {
                    String poster = posterObj.toString();
                    if (!poster.isEmpty() && !poster.equals("null")) {
                        filme.setUrlCapa("https://image.tmdb.org/t/p/w500" + poster);
                    }
                }
                return filme;
            }
        } catch (Exception e) {
            System.err.println("Erro ao buscar filme por ID: " + e.getMessage());
        }
        return null;
    }

    public List<Filme> buscarFilmesPorTitulo(String titulo) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "/search/movie?query=" + titulo + "&language=pt-BR&api_key=" + apiKey;
        
        List<Filme> lista = new ArrayList<>();
        try {
            @SuppressWarnings("unchecked")
            Map<String, Object> root = restTemplate.getForObject(url, Map.class);
            
            if (root != null && root.containsKey("results")) {
                @SuppressWarnings("rawtypes")
                List results = (List) root.get("results");
                
                for (Object item : results) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> node = (Map<String, Object>) item;
                    
                    Filme filme = new Filme();
                    filme.setId(Long.valueOf(node.get("id").toString()));
                    filme.setTitulo((String) node.get("title"));
                    
                    Object posterObj = node.get("poster_path");
                    if (posterObj != null && !posterObj.toString().equals("null")) {
                        filme.setUrlCapa("https://image.tmdb.org/t/p/w500" + posterObj.toString());
                    } else {
                        filme.setUrlCapa("https://via.placeholder.com/500x750?text=Sem+Capa"); 
                    }
                    
                    lista.add(filme);
                    if (lista.size() >= 5) break; 
                }
            }
        } catch (Exception e) {
            System.err.println("Erro na busca por nome: " + e.getMessage());
        }
        return lista;
    }
}