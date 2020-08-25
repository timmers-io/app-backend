package com.example.appbackend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class BackEndController {

	Logger logger = LoggerFactory.getLogger(BackEndController.class);

	@Autowired
	private PersonRepository repository;

	@RequestMapping("/")
	public String index() {
		// fetch all people
		logger.info("A TRACE Message");
		logger.info("People found with findAll():");
		logger.info("-------------------------------");
		for (Person person : repository.findAll()) {
			if (logger.isInfoEnabled()) {
				logger.info(String.format("person: %s", person));
			}
		}

		return "Greetings from VMworld 2020!!\n";
	}

	@PostMapping("/signup")
	public String signup(@RequestBody Person signupPerson) {
		logger.info("--------------------------------signup");
		if (logger.isInfoEnabled()) {
			logger.info(String.format("--- signupPerson: %s",signupPerson));
		}

		Person personFound = repository.findByEmailAndPassword(signupPerson.getEmail(),signupPerson.getPassword());
		
		if (logger.isInfoEnabled()) {
			logger.info(String.format("--- personFound: %s",personFound));
		} 
		
		if( personFound == null) {
			repository.save(signupPerson);
		}

		return "signup - VMworld 2020!\n";
	}


	@PostMapping("/login")
	public Person login(@RequestBody Person loginPerson) {
		logger.info("--------------------------------login");
		if (logger.isInfoEnabled()) {
			logger.info(String.format("--- loginPerson: %s",loginPerson));
		}

		Person personFound = repository.findByEmailAndPassword(loginPerson.getEmail(),loginPerson.getPassword());
		if( personFound == null) {
			throw new PersonNotFoundException(loginPerson.getEmail());
		} else {
			return personFound;
		}
	}


}