package br.ufcg.animais.animais_ufcg.repositories.animals;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import org.hibernate.validator.constraints.UUID;

import br.ufcg.animais.animais_ufcg.models.animals.*;

@Repository
public interface AnimalsRepository extends MongoRepository<Animal, UUID> {
    
}