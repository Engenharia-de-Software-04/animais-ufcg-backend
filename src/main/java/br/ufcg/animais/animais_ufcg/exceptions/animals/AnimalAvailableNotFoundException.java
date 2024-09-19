package br.ufcg.animais.animais_ufcg.exceptions.animals;

import br.ufcg.animais.animais_ufcg.exceptions.AnimaisUfcgException;

public class AnimalAvailableNotFoundException extends AnimaisUfcgException {
    public AnimalAvailableNotFoundException(){
        super("No animal available");
    }
}
