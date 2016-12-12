package dao;

import java.util.List;

import domain.Dbback;

public interface DbbackDao {

	void add(Dbback back);

	List<Dbback> list();

	Dbback find(String id);

}