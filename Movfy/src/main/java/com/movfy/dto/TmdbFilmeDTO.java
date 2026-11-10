package com.movfy.dto;

import lombok.Data;

@Data
public class TmdbFilmeDTO {
    private Long id;
    private String title;
    private String overview;
    private String poster_path;
}