package ro.fiipractic.health.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ro.fiipractic.health.dao.UserDAO;
import ro.fiipractic.health.domain.UserFromDB;

/**
 * 
 * @author Teodor
 *
 */
@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDAO userDao;

	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		UserFromDB userFromDB = userDao.findByUsername(username);
		
		if(userFromDB == null){
			throw new UsernameNotFoundException("User not found.");
		}
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for (String role : userFromDB.getRoles()) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		return new User(userFromDB.getUserName(), userFromDB.getPassword(),
				authorities);
	}

}
