package ro.fiipractic.health.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import ro.fiipractic.health.dao.UserDAO;
import ro.fiipractic.health.domain.UserFromDB;

public class DatabaseInitializer {

	@Autowired
	private UserDAO userDao;

	private PasswordEncoder passwordEncoder;
	
	protected DatabaseInitializer() {
		/* Default constructor for reflection instantiation */
	}

	public DatabaseInitializer(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}

	public void initDatabase() {
		UserFromDB userUser = new UserFromDB("user",
				this.passwordEncoder.encode("user"));
		userUser.addRole("ROLE_USER");
		this.userDao.saveOrUpdate(userUser);

		UserFromDB adminUser = new UserFromDB("admin",
				this.passwordEncoder.encode("admin"));
		adminUser.addRole("ROLE_USER");
		adminUser.addRole("ROLE_ADMIN");
		this.userDao.saveOrUpdate(adminUser);

	}

}
