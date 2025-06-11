package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizaCadastroTutorDto;
import br.com.alura.adopet.api.dto.CadastroDeTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    public void cadastrar(CadastroDeTutorDto dto) {
        boolean telefoneJaCadastrado = repository.existsByTelefone(dto.telefone());
        boolean emailJaCadastrado = repository.existsByEmail(dto.email());

        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        } else {
            Tutor tutor = new Tutor(dto);
            repository.save(tutor);
        }
    }

    public void atualizar(AtualizaCadastroTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.setTelefone(dto.telefone());
    }
}
