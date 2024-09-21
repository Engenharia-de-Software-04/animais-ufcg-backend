package br.ufcg.animais.animais_ufcg.exceptions.adoption_reports;

import br.ufcg.animais.animais_ufcg.exceptions.AnimaisUfcgException;

public class ReportNotFoundException extends AnimaisUfcgException {
    public ReportNotFoundException(){
        super("Report not registered");
    }
}
