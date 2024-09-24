package br.ufcg.animais.animais_ufcg.exceptions.adoption_reports;

import br.ufcg.animais.animais_ufcg.exceptions.AnimaisUfcgException;

public class AdoptionReportNotFound extends AnimaisUfcgException {
    public AdoptionReportNotFound(){
        super("Adoption report not found!");
    }  
}