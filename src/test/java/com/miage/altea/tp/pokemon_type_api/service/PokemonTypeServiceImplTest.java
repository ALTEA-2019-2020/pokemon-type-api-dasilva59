package com.miage.altea.tp.pokemon_type_api.service;

import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepository;
import com.miage.altea.tp.pokemon_type_api.repository.PokemonTypeRepositoryImpl;
import com.miage.altea.tp.pokemon_type_api.repository.TranslationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class PokemonTypeServiceImplTest {

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindById(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl();
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);

        pokemonTypeService.getPokemonType(25);

        verify(pokemonTypeRepository).findPokemonTypeById(25);
    }

    @Test
    void pokemonTypeRepository_shouldBeCalled_whenFindAll(){
        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        var pokemonTypeService = new PokemonTypeServiceImpl();
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        pokemonTypeService.getAllPokemonTypes();

        verify(pokemonTypeRepository).findAllPokemonType();
    }
    @Test
    void applicationContext_shouldLoadPokemonTypeService(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon_type_api");
        var serviceByName = context.getBean("pokemonTypeServiceImpl");
        var serviceByClass = context.getBean(PokemonTypeService.class);

        assertEquals(serviceByName, serviceByClass);
        assertNotNull(serviceByName);
        assertNotNull(serviceByClass);
    }

    @Test
    void pokemonTypeRepository_shouldBeAutowired_withSpring(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon_type_api");
        var service = context.getBean(PokemonTypeServiceImpl.class);
        assertNotNull(service.getPokemonTypeRepository());
    }
}