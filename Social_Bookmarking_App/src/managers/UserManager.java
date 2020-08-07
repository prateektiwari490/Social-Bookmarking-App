package managers;

import java.util.List;

import constants.Gender;
import dao.UserDao;
import entities.User;

/*
 * This Manager Class is singleton.
 * Singleton
 *  -- single object is created 
 *  -- global point of access
 */

public class UserManager {
	private static UserManager instance = new UserManager();
	private static UserDao userDao = new UserDao();
	
	private UserManager() {
	}

	public static UserManager getInstance() {
		return instance;
	}

	public User createUser(long id, String email, String password,
			String first_name, String last_name, Gender gender, String userType) {
		User user = new User();
		user.setId(id);
		user.setEmail(email);
		user.setPassword(password);
		user.setFirst_name(first_name);
		user.setLast_name(last_name);
		user.setGender(gender);
		user.setUserType(userType);
		
		return user;
	}
	
	public List<User> getUsers() {
		return userDao.getUsers();
	}
	
}
