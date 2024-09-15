package br.ufcg.animais.animais_ufcg.repositories.adoption_reports;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufcg.animais.animais_ufcg.models.adoption_reports.AdoptionReport;

@Repository
public interface AdoptionReportsRepository extends MongoRepository<AdoptionReport, String> {
    
}
