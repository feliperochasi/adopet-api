package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.CriacaoAbrigoDto;

public interface ValidacaoCadastroAbrigo {
    void validar(CriacaoAbrigoDto dto);
}
