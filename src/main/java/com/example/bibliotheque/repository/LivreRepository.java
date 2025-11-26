package com.example.bibliotheque.repository;

import com.example.bibliotheque.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Integer> {

    // Recherche les livres dont le titre contient le texte (sans tenir compte de la casse)
    List<Livre> findByTitreContainingIgnoreCase(String titre);
}
