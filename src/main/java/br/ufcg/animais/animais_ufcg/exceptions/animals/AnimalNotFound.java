package br.ufcg.animais.animais_ufcg.exceptions.animals;

import br.ufcg.animais.animais_ufcg.exceptions.AnimaisUfcgException;

public class AnimalNotFound extends AnimaisUfcgException {
    public AnimalNotFound(){
        super("Animal not found!");
    }
}