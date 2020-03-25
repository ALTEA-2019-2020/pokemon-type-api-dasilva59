package com.miage.altea.tp.pokemon_type_api.controller;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
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
    List<PokemonType> getPokemonTypeByName(@RequestParam (required = false)String name,@RequestParam (required = false)String types){
        if(name!=null){
            PokemonType p=ss.getPokemonTypeByName(name);
            return Arrays.asList(p);
        }
        if(types!=null){
            String[] result=types.split(",");
            List<String> r=Arrays.asList(result);
            return this.ss.getAllPokemonByTypes(r);
        }

        return null;
    }



    @CrossOrigin
@GetMapping("/")
    public List<PokemonType> getAllPokemonTypes() {
      List<PokemonType> p=new ArrayList<>();
      p=this.ss.getAllPokemonTypes();
    Collections.sort(p, Comparator.comparingLong(PokemonType::getId));
    return p;

    }
}
