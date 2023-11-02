package com.learn.boot.service;

import com.learn.boot.model.Tutorial;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface TutorialService {
    public ResponseEntity<Tutorial> createTutorial(Tutorial tutorial);

    public ResponseEntity<List<Tutorial>> getAllTutorials(String title);

    public ResponseEntity<List<Tutorial>> findByPublished();

    public ResponseEntity<HttpStatus> deleteAllTutorials();

    public ResponseEntity<HttpStatus> deleteTutorial(long id);

    public  ResponseEntity<Tutorial> updateTutorial(long id, Tutorial tutorial);

    public ResponseEntity<Tutorial> getTutorialById(long id);
}
