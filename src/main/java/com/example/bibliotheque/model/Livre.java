package com.example.bibliotheque.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Entity
@Table(name = "livres")
@Data

public class Livre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String titre;
    private  String auteur;
    private String genre;
    private Integer anneePublication;
    private  String isbn;


}
