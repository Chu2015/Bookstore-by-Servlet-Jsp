package dao;

import java.util.List;

import domain.Privilege;
import domain.User;

public interface UserDao {

	void add(User user);

	User find(String id);

	User find(String username, String password);

	List<Privilege> getAllPrivilege(User user);
}