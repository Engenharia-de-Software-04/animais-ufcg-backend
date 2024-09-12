package br.ufcg.animais.animais_ufcg.repositories.adoption_reports;

import org.springframework.data.mongodb.repository.MongoRepository;
import br.ufcg.animais.animais_ufcg.models.adoption_reports.AdoptionReport;
import java.util.*;

public interface AdoptionReportsRepository extends MongoRepository<AdoptionReport, UUID> {
    
}
