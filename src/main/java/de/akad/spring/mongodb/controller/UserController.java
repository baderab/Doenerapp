package de.akad.spring.mongodb.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import de.akad.spring.mongodb.model.UserModel;
import de.akad.spring.mongodb.repository.UserRepository;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	UserRepository userRepository;

	@GetMapping("/user")
	public ResponseEntity<List<UserModel>> getAllUsers(@RequestParam(required = false) String title) {
		try {
			List<UserModel> users = new ArrayList<UserModel>();

			if (title == null)
				userRepository.findAll().forEach(users::add);
			else
				userRepository.findByTitleContaining(title).forEach(users::add);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}

			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/user/{id}")
	public ResponseEntity<UserModel> getUserById(@PathVariable("id") String id) {
		Optional<UserModel> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			return new ResponseEntity<>(userData.get(), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping("/user")
	public ResponseEntity<UserModel> createUser(@RequestBody UserModel user) {
		try {
			UserModel _user = userRepository
					.save(new UserModel(user.getEmail(), user.getVorname(), user.getNachname(), user.getGeburtsdatum(),
							user.getPasswort(), user.getTitle(), user.getDescription(), false));
			return new ResponseEntity<>(_user, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
		}
	}

	@PutMapping("/user/{id}")
	public ResponseEntity<UserModel> updateUser(@PathVariable("id") String id, @RequestBody UserModel user) {
		Optional<UserModel> userData = userRepository.findById(id);

		if (userData.isPresent()) {
			UserModel _user = userData.get();
			_user.setTitle(user.getTitle());
			_user.setDescription(user.getDescription());
			_user.setEmail(user.getEmail());
			_user.setVorname(user.getVorname());
			_user.setNachname(user.getNachname());
			_user.setGeburtsdatum(user.getGeburtsdatum());
			_user.setPasswort(user.getPasswort());
			_user.setPublished(user.isPublished());
			return new ResponseEntity<>(userRepository.save(_user), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/user/{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") String id) {
		try {
			userRepository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@DeleteMapping("/user")
	public ResponseEntity<HttpStatus> deleteAllUsers() {
		try {
			userRepository.deleteAll();
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

	@GetMapping("/user/published")
	public ResponseEntity<List<UserModel>> findByPublished() {
		try {
			List<UserModel> users = userRepository.findByPublished(true);

			if (users.isEmpty()) {
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(users, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
		}
	}

}
