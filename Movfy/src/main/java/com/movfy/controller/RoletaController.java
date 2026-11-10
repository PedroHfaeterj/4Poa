package com.movfy.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movfy.model.Filme;
import com.movfy.service.RoletaFacade;

@RestController
@RequestMapping("/api/roleta")
@CrossOrigin(origins = "*") 
public class RoletaController {

    @Autowired
    private RoletaFacade roletaFacade;

    @GetMapping("/oscar")
    public Filme girarOscar() {
        return roletaFacade.girarRoletaOscar();
    }
}