package br.ufcg.animais.animais_ufcg.exceptions;

public class AnimaisUfcgException extends RuntimeException {

    public AnimaisUfcgException(){
        super("Unespectecd error on AnimaisUFCG aplication!");
    }
    public AnimaisUfcgException(String message) {
        super(message);
    }
}