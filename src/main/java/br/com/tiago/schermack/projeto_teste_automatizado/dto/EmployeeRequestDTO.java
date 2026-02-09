 package br.com.tiago.schermack.projeto_teste_automatizado.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public class EmployeeRequestDTO {

    @NotBlank(message = "O nome é obrigatório")
    private String firstName;

    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail é inválido")
    private String email;

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}
