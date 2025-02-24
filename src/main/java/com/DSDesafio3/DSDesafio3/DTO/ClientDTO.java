package com.DSDesafio3.DSDesafio3.DTO;

import com.DSDesafio3.DSDesafio3.entities.Client;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public class ClientDTO {

    private Long id;

    @NotBlank(message = "Campo Requerido")
    @Size(min = 2, max = 80)
    private String name;
    private String cpf;
    private Double income;
    @PastOrPresent(message = "data precisa ser menor ou igual a data atual")
    private LocalDate birthDate;
    private Integer children;

    public ClientDTO() {}

    public ClientDTO(Long id,String name, String cpf, Double income, LocalDate birthDate, Integer children) {
        this.id = id;
        this.name = name;
        this.cpf = cpf;
        this.income = income;
        this.birthDate = birthDate;
        this.children = children;
    }

    public ClientDTO(Client client) {
        this.id = client.getId();
        this.name = client.getName();
        this.cpf = client.getCpf();
        this.income = client.getIncome();
        this.birthDate = client.getBirthDate();
        this.children = client.getChildren();
    }

    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getCpf() {
        return cpf;
    }
    public Double getIncome() {
        return income;
    }
    public LocalDate getBirthDate() {
        return birthDate;
    }
    public Integer getChildren() {
        return children;
    }
}
