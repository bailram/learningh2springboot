package com.bailram.h2;

import com.bailram.h2.model.Person;
import com.bailram.h2.repository.PersonRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Optional;

@SpringBootApplication
public class H2Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(H2Application.class, args);
		// init person repository
		PersonRepository personRepository = context.getBean(PersonRepository.class);
		// init data
		Person person1 = new Person("Dhimas Bayu", "Malang");
		personRepository.save(person1);
		// should success
		Optional<Person> resultById = personRepository.findById(1L);
		if (resultById.isPresent()) {
			System.out.println(resultById.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
		// should fail
		Optional<Person> resultById1 = personRepository.findById(2L);
		if (resultById1.isPresent()) {
			System.out.println(resultById1.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
		// should success
		Person result2 = personRepository.findByName("Dhimas Bayu");
		if (result2 != null) {
			System.out.println(result2.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}
		// should fail
		Person result = personRepository.findByName("Dhimas");
		if (result != null) {
			System.out.println(result.toString());
		} else {
			System.out.println("Data tidak ditemukan");
		}

	}
}
