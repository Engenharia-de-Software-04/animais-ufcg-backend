package br.ufcg.animais.animais_ufcg.exceptions.animals;

import br.ufcg.animais.animais_ufcg.exceptions.AnimaisUfcgException;

public class AnimalNotFoundException extends AnimaisUfcgException {
    public AnimalNotFoundException(){
        super("No animals are currently registered");
    }
}