package br.com.alura.adopet.api.dto;

import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.TipoPet;

public record DetalhesPetDto(
        TipoPet tipo,
        String nome,
        String raca,
        Integer idade,
        String cor,
        Float peso
) {
    public DetalhesPetDto(Pet pet) {
        this(pet.getTipo(), pet.getNome(), pet.getRaca(), pet.getIdade(), pet.getCor(), pet.getPeso());
    }
}
