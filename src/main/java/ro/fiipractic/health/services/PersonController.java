package ro.fiipractic.health.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import ro.fiipractic.health.dao.PersonDAO;
import ro.fiipractic.health.domain.Person;

@Controller
@RequestMapping("/person")
public class PersonController {

	@Autowired
	private PersonDAO prsDao;

	@RequestMapping(value = "/get/{id}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody Person getUser(@PathVariable int id) {
		Person person = prsDao.findById(id);
		return person;
	}

	@RequestMapping(value = "/list", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Person> getAllUsers() {
		List<Person> persons = prsDao.findAll();
		return persons;
	}

	@RequestMapping(value = "/getByName/{name}", method = RequestMethod.GET, headers = "Accept=application/json")
	public @ResponseBody List<Person> getUserByName(@PathVariable String name) {
		List<Person> persons = prsDao.findByFirstName(name);
		return persons;
	}

	@RequestMapping(value = "/create", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody Person createPerson(@RequestBody Person person) {
		Person p = person;
		prsDao.save(p);
		return person;
	}

	@RequestMapping(value = "/createMany", method = RequestMethod.POST, headers = "Accept=application/json")
	public @ResponseBody List<Person> createPersons(
			@RequestBody List<Person> person) {
		List<Person> prss = person;
		for (Person p : prss) {
			prsDao.save(p);
		}
		return prss;
	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.PUT, headers = "Accept=application/json")
	public @ResponseBody String deletePerson(@PathVariable("id") int id) {
		Person prs = prsDao.findById(id);
		if (prs != null) {
			prsDao.delete(prs);
			return "{\"Status\":\"Success\"}";
		} else {
			return "{\"Status\":\"Error\", \"Message\":\"Can't find person with id="
					+ id + "\"}";
		}

	}

	public PersonDAO getPrsDao() {
		return prsDao;
	}

	public void setPrsDao(PersonDAO prsDao) {
		this.prsDao = prsDao;
	}

}
