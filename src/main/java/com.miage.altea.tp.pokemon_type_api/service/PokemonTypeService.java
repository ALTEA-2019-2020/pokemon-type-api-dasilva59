package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;

import java.util.List;

public interface PokemonTypeService {
    PokemonType getPokemonType(int id);
    List<PokemonType> getAllPokemonTypes();
    PokemonType getPokemonTypeByName(String name);
    List<PokemonType> getAllPokemonByTypes(List<String> a);
}