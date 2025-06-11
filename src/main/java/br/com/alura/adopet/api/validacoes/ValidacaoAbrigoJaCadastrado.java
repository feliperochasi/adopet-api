package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CriacaoAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoAbrigoJaCadastrado implements ValidacaoCadastroAbrigo {

    @Autowired
    private AbrigoRepository repository;

    public void validar(CriacaoAbrigoDto dto) {
        if (repository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
    }
}
