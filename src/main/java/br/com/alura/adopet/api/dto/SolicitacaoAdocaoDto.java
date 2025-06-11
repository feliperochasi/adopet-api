package br.com.alura.adopet.api.dto;

public record SolicitacaoAdocaoDto(
        Long idPet,
        Long idTutor,
        String motivo
) { }
