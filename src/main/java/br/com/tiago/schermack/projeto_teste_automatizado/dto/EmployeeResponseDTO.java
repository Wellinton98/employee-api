package br.com.tiago.schermack.projeto_teste_automatizado.dto;

public class EmployeeResponseDTO {

    private final Long id;
    private final String firstName;
    private final String email;

    public EmployeeResponseDTO(Long id, String firstName, String email) {
        this.id = id;
        this.firstName = firstName;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }
}