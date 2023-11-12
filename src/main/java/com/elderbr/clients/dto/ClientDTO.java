package com.elderbr.clients.dto;

import com.elderbr.clients.entities.Client;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class ClientDTO {
    private long id;

    @Size(min=3, max = 80, message = "O nome precisa ter 3 a 80 caracteres!")
    @NotBlank(message = "Campo requerido")
    private String name;

    @NotBlank(message = "Campo requerido")
    @CPF(message = "Verifique se digitou o CPF corretamente!")
    private String cpf;

    @Positive(message = "O valor não pode ser zero ou negativo")
    private double income;
    @NotNull(message = "Não pode ser nulo")
    @Past(message = "A data de nascimento deve estar no passado")
    @DateTimeFormat(pattern = "yyyy-MM-dd", iso = DateTimeFormat.ISO.DATE)
    private LocalDate birthDate;

    @PositiveOrZero(message = "O valor não pode ser negativo")
    private int children;

    private ClientDTO() {
    }

    public ClientDTO(long id, String name, String cpf, double income, LocalDate birthDate, int children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client){
        id = client.getId();
        name = client.getName();
        cpf = client.getCpf();
        income = client.getIncome();
        birthDate = client.getBirthDate();
        children = client.getChildren();
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCpf() {
        return cpf;
    }

    public double getIncome() {
        return income;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getChildren() {
        return children;
    }
}
