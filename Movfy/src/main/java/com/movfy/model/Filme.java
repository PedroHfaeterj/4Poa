package com.movfy.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Filme {
    
    @Id
    private Long id; 

    private String titulo;
    
    private Integer nota;
    
    private String comentarioIa;
    
    @Column(length = 2000)
    private String sinopse;
    
    private String urlCapa;
    
}