package br.com.compass.core.domain;

import java.sql.Date;

public class User {

    private final String name;
    private final Date birthDate;
    private final String cpf;
    private final String phone;
    private final String password;

    public User(String name, Date birthDate, String cpf, String phone, String password) {
        this.name = name;
        this.birthDate = birthDate;
        this.cpf = cpf;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return  birthDate;
    }

    public String getCpf() {
        return cpf;
    }

    public String getPhone() {
        return phone;
    }

    public String getPassword() {
        return password;
    }
}

