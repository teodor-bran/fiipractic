package ro.fiipractic.health.dao;

import ro.fiipractic.health.domain.UserFromDB;


public interface UserDAO {
	
	public UserFromDB findByUsername(String username);
	
	public void saveOrUpdate(UserFromDB userFromDB);

}
