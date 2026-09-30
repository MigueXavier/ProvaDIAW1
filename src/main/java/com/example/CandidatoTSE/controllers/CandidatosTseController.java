package com.example.CandidatoTSE.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.CandidatoTSE.models.Candidato;
import com.example.CandidatoTSE.services.CandidatosTseService;
import org.springframework.ui.Model;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CandidatosTseController {

    private final CandidatosTseService candidatosTseService;

   
    public CandidatosTseController(CandidatosTseService candidatosTseService) {
        this.candidatosTseService = candidatosTseService;
    }

    @GetMapping("/")
    public String index(
            @RequestParam(required = false) String cargo,
            @RequestParam(required = false) String partido,
            @RequestParam(required = false) String texto,
            Model model) {

        
        List<Candidato> candidatosFiltrados = candidatosTseService.filtrar(cargo, partido, texto);

       
        model.addAttribute("candidatos", candidatosFiltrados);

        
        model.addAttribute("totalCandidatos", candidatosFiltrados.size());

       
        model.addAttribute("listaCargos", candidatosTseService.listarCargos());
        model.addAttribute("listaPartidos", candidatosTseService.listarPartidos());

        
        model.addAttribute("cargoSelecionado", cargo != null ? cargo : "");
        model.addAttribute("partidoSelecionado", partido != null ? partido : "");
        model.addAttribute("textoDigitado", texto != null ? texto : "");

       
        return "index";
    }
}

