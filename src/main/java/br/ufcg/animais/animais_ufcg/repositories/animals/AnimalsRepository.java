package br.ufcg.animais.animais_ufcg.repositories.animals;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.ufcg.animais.animais_ufcg.models.animals.*;

@Repository
public interface AnimalsRepository extends MongoRepository<Animal, String> {
}
