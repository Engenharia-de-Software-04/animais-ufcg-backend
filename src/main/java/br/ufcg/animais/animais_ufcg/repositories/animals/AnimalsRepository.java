package br.ufcg.animais.animais_ufcg.repositories.animals;

import br.ufcg.animais.animais_ufcg.models.enumerations.AnimalStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import br.ufcg.animais.animais_ufcg.models.animals.*;
import java.util.List;

@Repository
public interface AnimalsRepository extends MongoRepository<Animal, String> {
    List<Animal> findByStatusAnimal(AnimalStatus status);

}
