package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CadastroDeTutorDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoTutorJaCadastrado implements ValidacaoCadastroTutor {

    @Autowired
    private TutorRepository repository;

    @Override
    public void validar(CadastroDeTutorDto dto) {
        if (repository.existsByEmailAndTelefone(dto.email(), dto.telefone())) {
            throw new ValidacaoException("Dados já cadastrados para outro tutor!");
        }
    }
}
