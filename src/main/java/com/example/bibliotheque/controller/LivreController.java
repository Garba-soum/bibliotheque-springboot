package com.example.bibliotheque.controller;

import com.example.bibliotheque.model.Livre;
import com.example.bibliotheque.service.LivreService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livres")
public class LivreController {

    private final LivreService livreService;


    public LivreController(LivreService livreService) {
        this.livreService = livreService;
    }

    @GetMapping
    public List<Livre> getAllLivres(){
        return livreService.getAllLivres();
    }

    @GetMapping("/{id}")
    public Livre getLivreById(@PathVariable Integer id){
        return livreService.getLivreById(id);
    }

    @PostMapping
    public Livre createLivre(@RequestBody Livre livre){
        return livreService.createLivre(livre);
    }

    @PutMapping("/{id}")
    public Livre updateLivre(@PathVariable Integer id, @RequestBody Livre livre){
        return livreService.updateLivre(id, livre);
    }

    @DeleteMapping("/{id}")
    public void deleteLivre(@PathVariable Integer id){
        livreService.deleteLivre(id);
    }


}
