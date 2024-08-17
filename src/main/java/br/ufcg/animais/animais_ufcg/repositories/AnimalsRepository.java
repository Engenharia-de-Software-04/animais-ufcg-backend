package br.ufcg.animais.animais_ufcg.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import br.ufcg.animais.animais_ufcg.models.*;

public interface AnimalsRepository extends MongoRepository<Animal, UUID> {
    
}
