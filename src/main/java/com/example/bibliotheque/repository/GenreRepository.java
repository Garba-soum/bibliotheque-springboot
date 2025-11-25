package com.example.bibliotheque.repository;

import com.example.bibliotheque.model.Genre;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Integer> {

}
