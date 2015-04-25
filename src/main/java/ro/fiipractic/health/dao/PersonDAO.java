package ro.fiipractic.health.dao;

import java.util.List;
import ro.fiipractic.health.domain.Person;


public interface PersonDAO {
	
	public List<Person> findAll();
	
	public Person findById(long id);
	
	public List<Person> findByFirstName(String firstName);
	
    public Person save(Person contact);
    
    public void delete(Person contact);
}
