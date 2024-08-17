package br.ufcg.animais.animais_ufcg.repositories.adoption_reports;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import br.ufcg.animais.animais_ufcg.models.*;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.AdoptionReport;

public interface AdoptionReportsRepository extends MongoRepository<AdoptionReport, UUID> {
    
}
