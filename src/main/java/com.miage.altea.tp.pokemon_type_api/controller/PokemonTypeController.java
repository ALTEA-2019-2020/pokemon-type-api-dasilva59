package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepositoryImpl;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping(value ="/pokemon-types")
public class PokemonTypeController {
    @Autowired
    private PokemonTypeService ss;

    public PokemonTypeController(PokemonTypeService s) {
        this.ss=s;

    }
@GetMapping(value="/{id}")
    PokemonType getPokemonTypeFromId(@PathVariable int id){
       return this.ss.getPokemonType(id);
    }

    @GetMapping()
    @ResponseBody
    List<PokemonType> getPokemonTypeByName(@RequestParam (required = false)String name,@RequestParam (required = false)String types){
        if(name!=null){
            List<PokemonType> result=new ArrayList<>();
            result.add(this.ss.getPokemonTypeByName(name));
            return result;

        }
        if(types!=null){
            String[] result=types.split(",");
            List<String> r=Arrays.asList(result);
            return this.ss.getAllPokemonByTypes(r);
        }
        return null;
    }




@GetMapping(value="/")
    public List<PokemonType> getAllPokemonTypes() {
      return this.ss.getAllPokemonTypes();
    }
}
