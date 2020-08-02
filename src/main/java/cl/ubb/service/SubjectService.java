package cl.ubb.service;

import java.util.List;

import cl.ubb.dao.exceptions.DeleteException;
import cl.ubb.dao.exceptions.UpdateException;
import cl.ubb.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cl.ubb.dao.SubjectDao;
import cl.ubb.dao.exceptions.CreateException;
import cl.ubb.model.Subject;

@Service
public class SubjectService {
	
	@Autowired
	private SubjectDao subjectDao;

	public List<Subject> getAll() throws EmptyListException {
		List<Subject> subjects;
		
		subjects = subjectDao.getAll();
		if (subjects.size() > 0) {
			return subjects;
		} else {
			throw new EmptyListException("There are no subjects");
		}
		
	}

	public Subject get(String id) throws ReadErrorException {
		Subject subject = subjectDao.get(id);
		if (subject != null) {
			return subject;
		} 
		else {
			throw new ReadErrorException("There is no a subject with given identifier");
		}
	}
	
	// Metodo incorporado solo por razones didacticas (no debieran crearse branches en el proyecto)
	public void create(Subject subject) throws CreateErrorException {
		try {
			subjectDao.create(subject);
		}
		catch(CreateException e) {
			throw new CreateErrorException("Can not create the branch with id: " + subject.getId());
		}
		
	}
	
	// Metodo incorporado solo por razones didacticas
	public void create(String id, String name) {
		try {
			subjectDao.create(new Subject(id,name));
		}
		catch (CreateException e) {
			
		}
		
	}

	public void update(Subject subject) throws UpdateErrorException {
		try {
			subjectDao.update(subject);
		} catch (UpdateException e) {
			throw new UpdateErrorException("Cannot update the branch with identifier: " + subject.getId());
		}
	}

	public void delete(String id) throws DeleteErrorException {
		try {
			subjectDao.delete(id);
		} catch (DeleteException e) {
			throw new DeleteErrorException("There is no a branch with given identifier");

		}
	}

}
