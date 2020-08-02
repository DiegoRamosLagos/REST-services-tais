package cl.ubb.dao;

import java.util.List;

import cl.ubb.dao.exceptions.CreateException;
import cl.ubb.dao.exceptions.DeleteException;
import cl.ubb.dao.exceptions.UpdateException;
import cl.ubb.model.Subject;

public interface SubjectDao {

	public Subject create(Subject item) throws CreateException;
	public Subject update(Subject item) throws UpdateException;
	public Subject delete(String id) throws DeleteException;
	public Subject get(String id);	
	public boolean exist(String id);
	public List<Subject> getAll();
	public void deleteAll();
}
