package pl.roomate.filters;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import roomate.filters.model.UserProfile;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FiltersApplicationTests {

	private UserRepository repository;
	private UserProfile user1;
	private UserProfile user2;
	private UserProfile user3;
	
	@Before 
	public void init(){
		user1 = new UserProfile();
		user1.setFirstName("John");
		user1.setLastName("Smith");
		user1.setAge(25);
		
		user2 = new UserProfile();
		user2.setFirstName("Anne");
		user2.setLastName("Luke");
		user2.setAge(37);
		
		user3 = new UserProfile();
		user3.setFirstName("Catherine");
		user3.setLastName("Johns");
		user3.setAge(29);
		
		repository.save(user1);
		repository.save(user2);
		repository.save(user3);
	}
	
	//Given: user lastname is set
	//When: getting list of users
	//Then: result correct
	@Test
	public void getUserByLastname(){
		UserSpecification specification = new UserSpecification(new SearchCriteria("lastname",":","Smith"));
		List<UserProfile> results = repository.findAll(specification);
		results.contains(user1);
		assertTrue(results.contains(user1));
		assertFalse(results.contains(user2));
		assertFalse(results.contains(user3));
	}

	
	
	@Test
	public void contextLoads() {
	}

}
