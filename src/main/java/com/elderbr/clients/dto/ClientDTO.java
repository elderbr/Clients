package com.elderbr.clients.dto;

import com.elderbr.clients.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

public class ClientDTO {
    private long id;

    @Size(min=3, max = 80, message = "O nome precisa ter 3 a 80 caracteres!")
    @NotBlank(message = "Campo requerido")
    private String name;

    @NotBlank(message = "Campo requerido")
    @CPF(message = "Verifique se digitou o CPF corretamente!")
    private String cpf;

    @Positive(message = "O valor não pode ser negativo")
    private double income;

    @Past(message = "A data do aniversário não pode ser atual ou futura")
    private LocalDate birthDate;

    @Positive(message = "O valor não pode ser negativo")
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
