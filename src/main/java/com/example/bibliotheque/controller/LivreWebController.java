package com.example.bibliotheque.controller;

import com.example.bibliotheque.model.Livre;
import com.example.bibliotheque.model.Genre;
import com.example.bibliotheque.repository.LivreRepository;
import com.example.bibliotheque.repository.GenreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/livres-web")
public class LivreWebController {

    @Autowired
    private LivreRepository livreRepository;

    @Autowired
    private GenreRepository genreRepository;

    // GET /livres-web  -> liste + recherche
    @GetMapping
    public String listeLivres(@RequestParam(name = "q", required = false) String q,
                              Model model) {

        if (q != null && !q.trim().isEmpty()) {
            model.addAttribute("livres", livreRepository.findByTitreContainingIgnoreCase(q));
        } else {
            model.addAttribute("livres", livreRepository.findAll());
        }

        model.addAttribute("q", q);  // pour réafficher la valeur saisie
        return "livres";
    }

    // GET /livres-web/ajouter  -> afficher formulaire
    @GetMapping("/ajouter")
    public String afficherFormulaireAjout(Model model) {
        model.addAttribute("livre", new Livre());
        model.addAttribute("genres", genreRepository.findAll());
        return "ajouter-livre";   // -> templates/ajouter-livre.html
    }

    // POST /livres-web/ajouter  -> enregistrer en base
    @PostMapping("/ajouter")
    public String traiterFormulaireAjout(@ModelAttribute Livre livre,
                                         @RequestParam("genreId") Integer genreId) {

        Genre genre = genreRepository.findById(genreId).orElse(null);
        livre.setGenre(genre);

        livreRepository.save(livre);
        return "redirect:/livres-web";
    }

    // GET /livres-web/editer/{id}  -> afficher le formulaire d’édition
    @GetMapping("/editer/{id}")
    public String afficherFormulaireEdition(@PathVariable("id") Integer id, Model model) {

        Livre livre = livreRepository.findById(id).orElse(null);
        if (livre == null) {
            // si l'id n'existe pas, on revient à la liste
            return "redirect:/livres-web";
        }

        model.addAttribute("livre", livre);
        model.addAttribute("genres", genreRepository.findAll());
        return "editer-livre";    // -> templates/editer-livre.html
    }

    // POST /livres-web/editer/{id} -> enregistrer les modifs
    @PostMapping("/editer/{id}")
    public String traiterFormulaireEdition(@PathVariable("id") Long id,
                                           @ModelAttribute Livre livre,
                                           @RequestParam("genreId") Integer genreId) {

        Genre genre = genreRepository.findById(genreId).orElse(null);
        livre.setGenre(genre);

        // on s’assure que l’ID reste bien celui passé dans l’URL
        livre.setId(id);

        livreRepository.save(livre);
        return "redirect:/livres-web";
    }

    // GET /livres-web/supprimer/{id} -> suppression
    @GetMapping("/supprimer/{id}")
    public String supprimerLivre(@PathVariable("id") Integer id) {
        livreRepository.deleteById(id);
        return "redirect:/livres-web";
    }
}
