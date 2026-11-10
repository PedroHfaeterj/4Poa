package com.movfy.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class IaService {

    public String gerarComentario(String tituloFilme) {
        RestTemplate restTemplate = new RestTemplate();
        
        // Criamos o prompt já pedindo em português
        String prompt = "Responda em português do Brasil. O usuário sorteou o filme '" + tituloFilme + "'. Escreva 1 frase curta, empolgante, divertida e sem spoilers, dizendo o porquê ele deve assistir a isso agora.";

        try {
            // A API do Pollinations funciona passando o texto direto na URL, 
            // então precisamos codificar os espaços e acentos (ex: transforma espaço em %20)
            String promptCodificado = URLEncoder.encode(prompt, StandardCharsets.UTF_8.toString());
            
            // URL da API pública e gratuita
            String url = "https://text.pollinations.ai/" + promptCodificado;

            // Faz a requisição GET e recebe a resposta da IA em formato de texto puro!
            String resposta = restTemplate.getForObject(url, String.class);

            if (resposta != null && !resposta.trim().isEmpty()) {
                return resposta;
            }

        } catch (Exception e) {
            System.err.println("Erro na IA: " + e.getMessage());
            return "Um excelente filme premiado! Vale a pena conferir."; 
        }
        
        return "Um excelente filme premiado! Vale a pena conferir.";
    }
    
    
    @Autowired
    private com.movfy.repository.FilmeRepository filmeRepository;

    public String recomendarBaseadoNoHistorico() {
        var filmes = filmeRepository.findAll();
        if (filmes.isEmpty()) return "Adicione filmes no seu diário primeiro!";

        // Extrai apenas os títulos da lista
        String titulos = filmes.stream().map(f -> f.getTitulo()).toList().toString();
        String prompt = "Responda em português do Brasil. Eu assisti e gostei destes filmes: " + titulos + ". Recomende apenas 1 filme diferente e diga o porquê de forma curta.";

        try {
            String promptCodificado = URLEncoder.encode(prompt, StandardCharsets.UTF_8.toString());
            return new RestTemplate().getForObject("https://text.pollinations.ai/" + promptCodificado, String.class);
        } catch (Exception e) {
            return "Erro ao buscar recomendação.";
        }
    }
}