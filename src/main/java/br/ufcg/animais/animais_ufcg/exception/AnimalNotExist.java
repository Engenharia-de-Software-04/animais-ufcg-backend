import br.ufcg.animais.animais_ufcg.AnimaisufcgException;

public class AnimalNotExist extends AnimaisufcgException{
    public AnimalNotExist(){
        super("Animal doesnt exist!");
    }
    
}
