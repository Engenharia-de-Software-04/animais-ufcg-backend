package br.ufcg.animais.animais_ufcg.repositories;

import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import br.ufcg.animais.animais_ufcg.domain.user.User;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByEmail(String email);

	default void delete(Optional<User> user) {
		user.ifPresent(this::delete);
	}
}
