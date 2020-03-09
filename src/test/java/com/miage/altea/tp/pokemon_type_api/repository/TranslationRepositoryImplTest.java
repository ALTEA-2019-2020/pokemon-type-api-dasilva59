package com.miage.altea.tp.pokemon_type_api.repository;

import com.miage.altea.tp.pokemon_type_api.bo.PokemonType;
import com.miage.altea.tp.pokemon_type_api.service.PokemonTypeServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TranslationRepositoryImplTest {

    private TranslationRepositoryImpl repository = new TranslationRepositoryImpl();

    @Test
    void getPokemonName_with1_inFrench_shouldReturnBulbizarre(){
        var name = repository.getPokemonName(1, Locale.FRENCH);
        assertEquals("Bulbizarre", name);
    }

    @Test
    void getPokemonName_with1_inEnglish_shouldReturnBulbizarre(){
        var name = repository.getPokemonName(1, Locale.ENGLISH);
        assertEquals("Bulbasaur", name);
    }

    @Test
    void applicationContext_shouldLoadPokemonRepository(){
        var context = new AnnotationConfigApplicationContext("com.miage.altea.tp.pokemon_type_api.repository");
        var repoByName = context.getBean("translationRepositoryImpl");
        var repoByClass = context.getBean(TranslationRepository.class);

        assertEquals(repoByName, repoByClass);
        assertNotNull(repoByName);
        assertNotNull(repoByClass);
    }



    @Test
    void pokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonTypeServiceImpl();

        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);
        when(pokemonTypeRepository.findPokemonTypeById(25)).thenReturn(new PokemonType());

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pikachu = pokemonTypeService.getPokemonType(25);

        assertEquals("Pikachu-FRENCH", pikachu.getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
    }

    @Test
    void allPokemonNames_shouldBeTranslated_usingLocaleResolver(){
        var pokemonTypeService = new PokemonTypeServiceImpl();

        var pokemonTypeRepository = mock(PokemonTypeRepository.class);
        pokemonTypeService.setPokemonTypeRepository(pokemonTypeRepository);

        var pikachu = new PokemonType();
        pikachu.setId(25);
        var raichu = new PokemonType();
        raichu.setId(26);
        when(pokemonTypeRepository.findAllPokemonType()).thenReturn(List.of(pikachu, raichu));

        var translationRepository = mock(TranslationRepository.class);
        pokemonTypeService.setTranslationRepository(translationRepository);
        when(translationRepository.getPokemonName(25, Locale.FRENCH)).thenReturn("Pikachu-FRENCH");
        when(translationRepository.getPokemonName(26, Locale.FRENCH)).thenReturn("Raichu-FRENCH");

        LocaleContextHolder.setLocale(Locale.FRENCH);

        var pokemonTypes = pokemonTypeService.getAllPokemonTypes();

        assertEquals("Pikachu-FRENCH", pokemonTypes.get(0).getName());
        assertEquals("Raichu-FRENCH", pokemonTypes.get(1).getName());
        verify(translationRepository).getPokemonName(25, Locale.FRENCH);
        verify(translationRepository).getPokemonName(26, Locale.FRENCH);
    }


}
