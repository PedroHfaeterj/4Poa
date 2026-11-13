package com.movfy.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;



@Service
public class IaService {

    
    
    @Autowired
    private com.movfy.repository.FilmeRepository filmeRepository;

    public String recomendarBaseadoLista(List<String> titulosAssistidos) {
        if (titulosAssistidos == null || titulosAssistidos.isEmpty()) {
            return "Adicione filmes no seu diário primeiro para eu te recomendar algo!";
        }

        String titulos = String.join(", ", titulosAssistidos);
        String prompt = "Responda em português do Brasil como se estivesse em um chat. Eu assisti: " + titulos + ". Recomende 1 filme diferente e diga o porquê de forma curta.";

        try {
            String promptCodificado = URLEncoder.encode(prompt, StandardCharsets.UTF_8.toString());
            return new RestTemplate().getForObject("https://text.pollinations.ai/" + promptCodificado, String.class);
        } catch (Exception e) {
            return "Erro ao buscar recomendação.";
        }
    
    }
    
    public String conversarNoChat(String mensagemUsuario, java.util.List<String> historicoFilmes) {
        String contexto = (historicoFilmes == null || historicoFilmes.isEmpty())
                ? "O usuário ainda não tem filmes salvos no diário."
                : "Filmes no diário do usuário: " + String.join(", ", historicoFilmes) + ".";

        String prompt = "Você é a CinemIA, uma assistente especialista em cinema do site Movfy. "
                + "Responda em português do Brasil de forma curta, amigável e direta. "
                + contexto + " O usuário disse: '" + mensagemUsuario + "'";

        try {
            String promptCodificado = URLEncoder.encode(prompt, StandardCharsets.UTF_8.toString());
            return new RestTemplate().getForObject("https://text.pollinations.ai/" + promptCodificado, String.class);
        } catch (Exception e) {
            return "Ops, tive um problema de conexão ao pensar na resposta.";
        }
    }
    
}