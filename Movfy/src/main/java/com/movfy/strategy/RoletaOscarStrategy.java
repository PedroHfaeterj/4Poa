package com.movfy.strategy;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import org.springframework.stereotype.Service;

@Service
public class RoletaOscarStrategy implements RoletaStrategy {
    
    // Todos os 97 vencedores do Oscar de Melhor Filme (1927 a 2024) no TMDB
    private static final List<Long> VENCEDORES_OSCAR = Arrays.asList(
        1064213L, // 2024: Anora
        872585L,  // 2023: Oppenheimer
        545611L,  // 2022: Tudo em Todo o Lugar ao Mesmo Tempo
        776503L,  // 2021: No Ritmo do Coração (CODA)
        581734L,  // 2020: Nomadland
        496243L,  // 2019: Parasita
        490132L,  // 2018: Green Book: O Guia
        399055L,  // 2017: A Forma da Água
        376867L,  // 2016: Moonlight: Sob a Luz do Luar
        314365L,  // 2015: Spotlight: Segredos Revelados
        194662L,  // 2014: Birdman
        76203L,   // 2013: 12 Anos de Escravidão
        68734L,   // 2012: Argo
        74643L,   // 2011: O Artista
        45269L,   // 2010: O Discurso do Rei
        12162L,   // 2009: Guerra ao Terror
        12405L,   // 2008: Quem Quer Ser um Milionário?
        6977L,    // 2007: Onde os Fracos Não Têm Vez
        1422L,    // 2006: Os Infiltrados
        1640L,    // 2005: Crash: No Limite
        70L,      // 2004: Menina de Ouro
        122L,     // 2003: O Senhor dos Anéis: O Retorno do Rei
        1574L,    // 2002: Chicago
        453L,     // 2001: Uma Mente Brilhante
        98L,      // 2000: Gladiador
        14L,      // 1999: Beleza Americana
        1934L,    // 1998: Shakespeare Apaixonado
        597L,     // 1997: Titanic
        409L,     // 1996: O Paciente Inglês
        197L,     // 1995: Coração Valente
        13L,      // 1994: Forrest Gump
        424L,     // 1993: A Lista de Schindler
        33L,      // 1992: Os Imperdoáveis
        274L,     // 1991: O Silêncio dos Inocentes
        581L,     // 1990: Dança com Lobos
        403L,     // 1989: Conduzindo Miss Daisy
        380L,     // 1988: Rain Man
        746L,     // 1987: O Último Imperador
        792L,     // 1986: Platoon
        606L,     // 1985: Entre Dois Amores
        279L,     // 1984: Amadeus
        11050L,   // 1983: Laços de Ternura
        783L,     // 1982: Gandhi
        9443L,    // 1981: Carruagens de Fogo
        16619L,   // 1980: Gente Como a Gente
        12102L,   // 1979: Kramer vs. Kramer
        11778L,   // 1978: O Franco Atirador
        703L,     // 1977: Noivo Neurótico, Noiva Nervosa
        1366L,    // 1976: Rocky: Um Lutador
        510L,     // 1975: Um Estranho no Ninho
        240L,     // 1974: O Poderoso Chefão: Parte II
        9277L,    // 1973: Golpe de Mestre
        238L,     // 1972: O Poderoso Chefão
        1051L,    // 1971: Operação França
        11202L,   // 1970: Patton: Rebelde ou Herói?
        3116L,    // 1969: Perdidos na Noite
        17917L,   // 1968: Oliver!
        10633L,   // 1967: No Calor da Noite
        874L,     // 1966: O Homem que Não Vendeu Sua Alma
        15121L,   // 1965: A Noviça Rebelde
        11113L,   // 1964: Minha Bela Dama
        5769L,    // 1963: As Aventuras de Tom Jones
        947L,     // 1962: Lawrence da Arábia
        1725L,    // 1961: Amor, Sublime Amor
        284L,     // 1960: Se Meu Apartamento Falasse
        665L,     // 1959: Ben-Hur
        17281L,   // 1958: Gigi
        826L,     // 1957: A Ponte do Rio Kwai
        2897L,    // 1956: A Volta ao Mundo em 80 Dias
        15919L,   // 1955: Marty
        654L,     // 1954: Sindicato de Ladrões
        11426L,   // 1953: A Um Passo da Eternidade
        27191L,   // 1952: O Maior Espetáculo da Terra
        2769L,    // 1951: Sinfonia de Paris
        705L,     // 1950: A Malvada
        25430L,   // 1949: A Grande Ilusão
        23383L,   // 1948: Hamlet
        33667L,   // 1947: A Luz é Para Todos
        887L,     // 1946: Os Melhores Anos de Nossas Vidas
        28580L,   // 1945: Farrapo Humano
        17661L,   // 1944: O Bom Pastor
        289L,     // 1943: Casablanca
        27367L,   // 1942: Rosa de Esperança
        43266L,   // 1941: Como Era Verde o Meu Vale
        223L,     // 1940: Rebecca, a Mulher Inesquecível
        770L,     // 1939: ...E o Vento Levou
        34106L,   // 1938: Do Mundo Nada Se Leva
        43278L,   // 1937: A Vida de Émile Zola
        43277L,   // 1936: Ziegfeld: O Criador de Estrelas
        12311L,   // 1935: O Grande Motim
        3078L,    // 1934: Aconteceu Naquela Noite
        43272L,   // 1933: Cavalgada
        27845L,   // 1932: Grande Hotel
        42861L,   // 1931: Cimarrón
        143L,     // 1930: Sem Novidade no Front
        65203L,   // 1929: Melodia da Broadway
        28966L    // 1927: Asas (Wings)
    );

    @Override
    public Long sortearFilmeId() {
        Random random = new Random();
        int index = random.nextInt(VENCEDORES_OSCAR.size());
        return VENCEDORES_OSCAR.get(index);
    }
}