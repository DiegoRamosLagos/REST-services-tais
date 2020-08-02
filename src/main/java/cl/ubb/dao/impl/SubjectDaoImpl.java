package cl.ubb.dao.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.google.gson.Gson;

import cl.ubb.dao.SubjectDao;
import cl.ubb.dao.exceptions.CreateException;
import cl.ubb.dao.exceptions.DeleteException;
import cl.ubb.dao.exceptions.UpdateException;
import cl.ubb.model.Subject;

@Repository
public class SubjectDaoImpl implements SubjectDao {

	private void clear(){
		try{
			BufferedWriter output = new BufferedWriter(new FileWriter("SubjectDao.txt"));			
			output.close();
		}catch (Exception e){
			//
		}
	}

	private void write(Subject branch) throws CreateException {
		if(!exist(branch.getId())){
			Gson gson = new Gson();
			String json = gson.toJson(branch); 		
			try{
				BufferedWriter output = new BufferedWriter(new FileWriter("SubjectDao.txt", true));			
				output.write(json+"\n");
				output.close();
			}catch (Exception e){
			}		
		}
		else {
			throw new CreateException();
		}
	}

	private List<Subject> readAll(){
		List <Subject> branches = new ArrayList<Subject>();
		String json = "";
		BufferedReader reader = null;
		Gson gson = new Gson();
		try {
			reader = new BufferedReader(new InputStreamReader(new FileInputStream("SubjectDao.txt")));
			while ((json = reader.readLine()) != null) {
				//System.out.println("JSON: " + json);
				Subject branch = gson.fromJson(json, Subject.class);
				branches.add(branch);
			}
			reader.close();
		}catch(Exception e){
			//todo

		}		
		return branches;
	}
	
	@Override
	public Subject create(Subject subject) throws CreateException {
		write(subject);
		return subject;
	}

	@Override
	public Subject update(Subject updatedSubject) throws UpdateException {
		Subject updated = null;
		List <Subject> subjects = readAll();
		List <Subject> newSubjectes = new ArrayList<Subject>();
		for(Subject s : subjects){
			if(s.getId().equals(updatedSubject.getId())){
				newSubjectes.add(updatedSubject);
				updated = s;
			}else{
				newSubjectes.add(s);
			}
		}
		if(updated == null) {
			throw new UpdateException();
		}else{
			try {
				clear();
				for(Subject subject : newSubjectes){
					write(subject);
				}
			}
			catch(CreateException e) {
				throw new UpdateException();
			}
		}

		return updated;
	}

	@Override
	public Subject delete(String id) throws DeleteException {
		Subject deleted=null;
		List <Subject> subjects = readAll();
		List <Subject> newSubjectes = new ArrayList<Subject>();
		for(Subject subject : subjects){
			if(!id.equals(subject.getId()))
				newSubjectes.add(subject);
			else
				deleted = subject;
		}
		if(deleted == null) {
			throw new DeleteException();
		}else{
			try {
				clear();
				for(Subject subject : newSubjectes){
					write(subject);
				}
			}
			catch(CreateException e) {
				throw new DeleteException();
			}
		}
		return deleted;
	}

	@Override
	public Subject get(String id) {
		List <Subject> subjects = readAll();
		for(Subject subject : subjects){
			if(id.equals(subject.getId()))
				return subject;
		}
		return null;
	}

	@Override
	public boolean exist(String id) {
		List <Subject> subjects = readAll();
		for(Subject subject : subjects){
			if(id.equals(subject.getId()))
				return true;
		}
		return false;
	}

	@Override
	public List<Subject> getAll() {
		//System.out.println("All");
		return readAll();
	}

	@Override
	public void deleteAll() {
		clear();
	}
	
}
