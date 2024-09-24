package br.ufcg.animais.animais_ufcg.controllers.user;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.ufcg.animais.animais_ufcg.domain.user.User;
import br.ufcg.animais.animais_ufcg.dto.LoginRequestDTO;
import br.ufcg.animais.animais_ufcg.dto.LoginResponseDTO;
import br.ufcg.animais.animais_ufcg.dto.RegisterRequestDTO;
import br.ufcg.animais.animais_ufcg.infra.security.TokenService;
import br.ufcg.animais.animais_ufcg.repositories.UserRepository;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private TokenService tokenService;
	
	@PostMapping("/login")
	public ResponseEntity login(@RequestBody LoginRequestDTO body) {
		User user = this.userRepository.findByEmail(body.email()).orElseThrow(() -> new RuntimeException("User not found"));
		if (passwordEncoder.matches(body.password(), user.getPassword())) {
			String token = this.tokenService.generateToken(user);
			return ResponseEntity.ok(new LoginResponseDTO(user.getName(), token));
		} 
		
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody RegisterRequestDTO body) {
		Optional<User> user = this.userRepository.findByEmail(body.email());
		if (user.isEmpty()) {
			User newUser = new User();
			newUser.setPassword(this.passwordEncoder.encode(body.password()));
			newUser.setEmail(body.email());
			newUser.setName(body.name());
			this.userRepository.save(newUser);
			String token = this.tokenService.generateToken(newUser);
			return ResponseEntity.ok(new LoginResponseDTO(newUser.getName(), token)); 			
		}
		
		return ResponseEntity.status(400).body("Email already exists");
	}
}
