package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

        public static PokemonTypeRepository pokemonTypeRepository;

    public PokemonTypeServiceImpl(PokemonTypeRepository r){
            this.pokemonTypeRepository=r;
    }

    @Override
    public PokemonType getPokemonType(int id) {
       return this.pokemonTypeRepository.findPokemonTypeById(id);
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        return this.pokemonTypeRepository.findAllPokemonType();
    }

    @Override
    public  PokemonType getPokemonTypeByName(String name){
        return pokemonTypeRepository.findPokemonTypeByName(name);
    }
    @Override
    public List<PokemonType> getAllPokemonByTypes(List<String> a){
        return pokemonTypeRepository.findAllPokemonByType(a);
    }
}