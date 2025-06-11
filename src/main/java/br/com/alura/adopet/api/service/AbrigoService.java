package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CriacaoAbrigoDto;
import br.com.alura.adopet.api.dto.CriacaoPetDto;
import br.com.alura.adopet.api.dto.DetalhesAbrigoDto;
import br.com.alura.adopet.api.dto.DetalhesPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastroAbrigo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository repository;

    @Autowired
    private List<ValidacaoCadastroAbrigo> validacoes;

    public List<DetalhesAbrigoDto> listar() {
        return repository.findAll().stream().map(DetalhesAbrigoDto::new).toList();
    }

    public void cadastrar(CriacaoAbrigoDto dto) {

        validacoes.forEach(v -> v.validar(dto));

        Abrigo abrigo = new Abrigo(dto);
        repository.save(abrigo);

    }

    public List<DetalhesPetDto> listarPets(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            return repository.getReferenceById(id).getPets().stream().map(DetalhesPetDto::new).toList();
        } catch (EntityNotFoundException enfe) {
            throw new EntityNotFoundException(enfe);
        } catch (NumberFormatException e) {
            try {
                return repository.findByNome(idOuNome).getPets().stream().map(DetalhesPetDto::new).toList();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException(enfe);
            }
        }
    }

    public void cadastrarPet(String idOuNome, CriacaoPetDto dto) {
        Pet pet = new Pet(dto);
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = repository.getReferenceById(id);
            abrigo.getPets().add(pet);
            repository.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new EntityNotFoundException(enfe);
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = repository.findByNome(idOuNome);
                abrigo.getPets().add(pet);
                repository.save(abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException(enfe);
            }
        }
    }
}
