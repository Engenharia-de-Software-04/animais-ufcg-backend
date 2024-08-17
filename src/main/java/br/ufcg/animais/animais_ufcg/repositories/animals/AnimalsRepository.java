package br.ufcg.animais.animais_ufcg.repositories.animals;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import br.ufcg.animais.animais_ufcg.models.animals.Animal;

public interface AnimalsRepository extends MongoRepository<Animal, UUID> {
    
}
