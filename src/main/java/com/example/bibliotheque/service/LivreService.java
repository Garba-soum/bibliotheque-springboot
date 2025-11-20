package com.example.bibliotheque.service;

import com.example.bibliotheque.model.Livre;
import com.example.bibliotheque.repository.LivreRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LivreService {

    private final LivreRepository livreRepository;
    public LivreService(LivreRepository livreRepository) {
        this.livreRepository = livreRepository;
    }

    public List<Livre> getAllLivres(){
        return livreRepository.findAll();
    }

    public Livre getLivreById(Long id){
        Optional<Livre> opt = livreRepository.findById(id);
        return opt.orElse(null);
    }

    public Livre createLivre(Livre livre){
        return livreRepository.save(livre);
    }

    public Livre updateLivre(Long id, Livre livreDetails){
        Optional<Livre> opt = livreRepository.findById(id);

        if(opt.isPresent()){
            Livre livre = opt.get();
            livre.setTitre(livreDetails.getTitre());
            livre.setAuteur(livreDetails.getAuteur());
            livre.setGenre(livreDetails.getGenre());
            livre.setAnneePublication(livreDetails.getAnneePublication());
            livre.setIsbn(livreDetails.getIsbn());
            return livreRepository.save(livre);

        }else {
            return null;
        }
    }

    public void deleteLivre(Long id){
        livreRepository.deleteById(id);
    }


}
