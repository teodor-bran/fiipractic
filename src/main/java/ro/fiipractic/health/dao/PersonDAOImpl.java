package ro.fiipractic.health.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.fiipractic.health.domain.Person;

@Repository(value = "personDao")
@Transactional
public class PersonDAOImpl implements PersonDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public List<Person> findAll() {
		return sessionFactory.getCurrentSession().createQuery("from Person p")
				.list();
	}

	public Person save(Person contact) {
		sessionFactory.getCurrentSession().saveOrUpdate(contact);
		return contact;
	}

	public void delete(Person contact) {
		sessionFactory.getCurrentSession().delete(contact);
	}

	public Person findById(long id) {
		List<Person> prss = sessionFactory.getCurrentSession()
				.createQuery("FROM Person where id =" + id).list();
		try {
			Person result = prss.get(0);
			return result;
		} catch (IndexOutOfBoundsException e) {
			System.err.println("No person with id: " + id);
			return null;
		}
	}

	/**
	 * Ar putea exista mai multe persoane cu acelasi prenume
	 * 
	 * @param firstName
	 *            prenumele persoanei cautate
	 * @return lista cu persoane ce au prenumele egal cu <b>firstName</b>
	 */
	public List<Person> findByFirstName(String firstName) {
		String queryString = "FROM Person where firstname = :firstName";
		Query query = sessionFactory.getCurrentSession().createQuery(
				queryString);
		query.setParameter("firstName", firstName);
		List<Person> results = query.list();
		return results;

	}

}
