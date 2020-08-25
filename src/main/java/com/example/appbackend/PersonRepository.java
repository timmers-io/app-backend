
package com.example.appbackend;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface PersonRepository extends MongoRepository<Person, String> {

	List<Person> findByLastName(@Param("name") String name);
	Person findByEmail(@Param("email") String email);
	Person findByName(@Param("name") String name);
	Person findByEmailAndPassword(@Param("email") String email,@Param("password") String password);

}
