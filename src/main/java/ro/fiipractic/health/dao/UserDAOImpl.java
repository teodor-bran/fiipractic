package ro.fiipractic.health.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ro.fiipractic.health.domain.UserFromDB;

@Repository("userDao")
@Transactional(readOnly = false)
public class UserDAOImpl implements UserDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public UserFromDB findByUsername(String userName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				UserFromDB.class);
		criteria.add(Restrictions.eq("userName", userName));
		return (UserFromDB) criteria.uniqueResult();
	}

	@Transactional(readOnly = false)
	public void saveOrUpdate(UserFromDB userFromDB) {
		sessionFactory.getCurrentSession().saveOrUpdate(userFromDB);
	}

}
