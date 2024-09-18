package br.ufcg.animais.animais_ufcg.exception.animals;

import br.ufcg.animais.animais_ufcg.exception.AnimaisUfcgException;

public class AnimalAvailableNotFoundException extends AnimaisUfcgException {
    public AnimalAvailableNotFoundException(){
        super("No animal available");
    }
}
