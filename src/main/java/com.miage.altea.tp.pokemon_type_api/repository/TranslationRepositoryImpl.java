package com.miage.altea.tp.pokemon_type_api.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.miage.altea.tp.pokemon_type_api.bo.Translation;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Repository
public class TranslationRepositoryImpl implements TranslationRepository {

    private Map<Locale, List<Translation>> translations;

    private List<Translation> defaultTranslations;

    public TranslationRepositoryImpl() {
        try {
            var objectMapper = new ObjectMapper();

            var frenchTranslationStream = new ClassPathResource("translations-fr.json").getInputStream();
            var frenchTranslationsArray = objectMapper.readValue(frenchTranslationStream, Translation[].class);

            var englishTranslationStream = new ClassPathResource("translations-en.json").getInputStream();
            var englishTranslationsArray = objectMapper.readValue(englishTranslationStream, Translation[].class);

            this.translations = Map.of(
                    Locale.FRENCH, List.of(frenchTranslationsArray),
                    Locale.ENGLISH, List.of(englishTranslationsArray)
            );

            this.defaultTranslations = List.of(englishTranslationsArray);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getPokemonName(int id, Locale locale) {
        List<Translation> l=new ArrayList<Translation>();
        if(locale.equals(Locale.FRENCH)) {
            l = this.translations.get(locale);
            for (Translation t : l) {
                if (t.getId() == id)
                    return t.getName();
            }
        }
        else {
            for (Translation t : this.defaultTranslations) {
                if (t.getId() == id)
                    return t.getName();
            }
        }
        return null;
    }
}