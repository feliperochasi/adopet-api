package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.AtualizaCadastroTutorDto;
import br.com.alura.adopet.api.dto.CadastroDeTutorDto;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import br.com.alura.adopet.api.validacoes.ValidacaoCadastroTutor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService {
    @Autowired
    private TutorRepository repository;

    @Autowired
    private List<ValidacaoCadastroTutor> validacoes;

    public void cadastrar(CadastroDeTutorDto dto) {

        validacoes.forEach(v -> v.validar(dto));

        Tutor tutor = new Tutor(dto);
        repository.save(tutor);

    }

    public void atualizar(AtualizaCadastroTutorDto dto) {
        Tutor tutor = repository.getReferenceById(dto.id());
        tutor.atualizaDadosTutor(dto);
    }
}
