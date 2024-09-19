package br.ufcg.animais.animais_ufcg.exception;

public class AnimaisufcgException extends RuntimeException{
    public AnimaisufcgException(){
        super("Erro inesperado no Animaisufcg!");
    }
    public AnimaisufcgException(String message){
        super(message);
    }
    
}
