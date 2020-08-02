package cl.ubb.controller;

import cl.ubb.dao.exceptions.UpdateException;
import cl.ubb.model.Subject;
import cl.ubb.service.SubjectService;
import cl.ubb.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @GetMapping("/list")
    public ResponseEntity<?> getAll() {
        ResponseEntity<?>response;
        try {
            List<Subject> subjects = subjectService.getAll();
            response = new ResponseEntity<>(subjects, HttpStatus.OK);
        } catch (EmptyListException e) {
            response = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return response;
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getSubject(@PathVariable String id) {
        Subject subject = null;
        try {
            subject = subjectService.get(id);
        } catch (ReadErrorException e) {
            return new ResponseEntity<>(String.format("Subject with id %s not found", id), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSubject(@RequestBody Subject subject) {
        try {
            subjectService.create(subject);
        } catch (CreateErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity<>(subject, HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateSubject(@RequestBody Subject subject) {
        try {
            subjectService.update(subject);
        } catch (UpdateErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(subject, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteSubject(@PathVariable String id) {
        try {
            subjectService.delete(id);
        } catch (DeleteErrorException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

