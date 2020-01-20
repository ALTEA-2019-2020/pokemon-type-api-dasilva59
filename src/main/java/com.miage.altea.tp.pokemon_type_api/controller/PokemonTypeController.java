package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping(value ="/pokemon-types")
public class PokemonTypeController {
    private PokemonTypeService ss;
    public PokemonTypeController(PokemonTypeService s) {
        this.ss=s;
    }
@GetMapping(value="/{id}")
    PokemonType getPokemonTypeFromId(int id){
       return this.ss.getPokemonType(id);
    }
@GetMapping(value="/")
    public List<PokemonType> getAllPokemonTypes() {
      return this.ss.getAllPokemonTypes();
    }
}
