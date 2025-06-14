package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record AtualizaCadastroTutorDto(
        @NotNull
        Long id,
        @NotBlank
        @Pattern(regexp = "\\(?\\d{2}\\)?\\d?\\d{4}-?\\d{4}")
        String telefone) {
}
