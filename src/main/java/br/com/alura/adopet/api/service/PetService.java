package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.DetalhesPetDto;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PetService {

    @Autowired
    private PetRepository repository;

    public List<DetalhesPetDto> listarTodosDisponiveis () {
        List<Pet> pets = repository.findByAdotado(false);
        return pets.stream().map(DetalhesPetDto::new).toList();
    }
}
