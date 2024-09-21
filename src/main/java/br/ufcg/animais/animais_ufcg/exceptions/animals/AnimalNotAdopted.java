package br.ufcg.animais.animais_ufcg.exceptions.animals;

import br.ufcg.animais.animais_ufcg.exceptions.AnimaisUfcgException;

public class AnimalNotAdopted extends AnimaisUfcgException {
    public AnimalNotAdopted(){
        super("Animal wasn't adopted!");
    }  
}