package dao;

import java.util.List;

import Default.DataStore;
import entities.User;

public class UserDao {
	public List<User> getUsers() {
		return DataStore.getUsers();
	}
}
