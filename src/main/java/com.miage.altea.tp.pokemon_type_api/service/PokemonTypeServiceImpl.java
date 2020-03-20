package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepositoryImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class PokemonTypeServiceImpl implements PokemonTypeService{

        private PokemonTypeRepository pokemonTypeRepository;

        @Autowired
        private TranslationRepository translationRepository;

    public PokemonTypeServiceImpl(){this.translationRepository=new TranslationRepositoryImpl(); }

    @Override
    public PokemonType getPokemonType(int id) {
        Locale loc=LocaleContextHolder.getLocale();
        String n=this.translationRepository.getPokemonName(id,loc);
        PokemonType p=this.pokemonTypeRepository.findPokemonTypeById(id);
        if(p!=null)
        p.setName(n);
        return p;
    }

    @Override
    public List<PokemonType> getAllPokemonTypes(){
        List<PokemonType> l=new ArrayList<>();
        l=this.getPokemonTypeRepository().findAllPokemonType();
        Locale loc=LocaleContextHolder.getLocale();

        for(PokemonType p :l){
            String n=this.translationRepository.getPokemonName(p.getId(),loc);
            p.setName(n);
        }
        return  l;
    }

    @Override
    public  PokemonType getPokemonTypeByName(String name){
        return getPokemonTypeRepository().findPokemonTypeByName(name);
    }
    @Override
    public List<PokemonType> getAllPokemonByTypes(List<String> a){
        return getPokemonTypeRepository().findAllPokemonByType(a);
    }

    public PokemonTypeRepository getPokemonTypeRepository() {
        return pokemonTypeRepository;
    }

    @Autowired
    public void setPokemonTypeRepository(PokemonTypeRepository pokemonTypeRepository) {
        this.pokemonTypeRepository = pokemonTypeRepository;
    }

    @Autowired
    public void setTranslationRepository(TranslationRepository translationRepository) {
        this.translationRepository=translationRepository;
    }
    public TranslationRepository getTranslationRepository() {
        return this.translationRepository;
    }

}