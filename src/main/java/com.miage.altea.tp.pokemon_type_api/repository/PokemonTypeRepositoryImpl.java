package com.miage.altea.tp.pokemon_type_api.repository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Repository
public class PokemonTypeRepositoryImpl implements PokemonTypeRepository {

    private List<PokemonType> pokemons;

    public PokemonTypeRepositoryImpl() {
        try {
            var pokemonsStream = new ClassPathResource("pokemons.json").getInputStream();
            var objectMapper = new ObjectMapper();
            var pokemonsArray = objectMapper.readValue(pokemonsStream, PokemonType[].class);
            this.pokemons = Arrays.asList(pokemonsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public PokemonType findPokemonTypeById(int id) {
        System.out.println("Loading Pokemon information for Pokemon id " + id);
        for(PokemonType p :this.pokemons)
        {
            if(p.getId()==id)
                return p;
        }
        return null;
    }

    @Override
    public PokemonType findPokemonTypeByName(String name) {
        System.out.println("Loading Pokemon information for Pokemon name " + name);
        for(PokemonType p :this.pokemons)
        {
            if(p.getName().toUpperCase().equals(name.toUpperCase()))
                return p;
        }
        return null;
    }

    @Override
    public List<PokemonType> findAllPokemonType() {

        return this.pokemons;
    }

    @Override
    public List<PokemonType> findAllPokemonByType(List<String> types){
        List<PokemonType> found=new ArrayList<>();
        for(PokemonType p :this.pokemons) {
            if(p.getTypes().containsAll(types))
                found.add(p);
        }
        return found;
    }

}