package dao;

import java.util.List;

import domain.Category;

public interface CategoryDao {

	void add(Category c);

	Category find(String id);

	List getAll();

}