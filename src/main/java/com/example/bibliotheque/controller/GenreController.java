package com.example.bibliotheque.controller;

import com.example.bibliotheque.model.Genre;
import com.example.bibliotheque.repository.GenreRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/genres")
@CrossOrigin

public class GenreController {

    private final GenreRepository genreRepository;

    public GenreController(GenreRepository genreRepository){

        this.genreRepository = genreRepository;
    }

    @GetMapping
    public List<Genre> getAllGenres(){
        return genreRepository.findAll();
    }


}
