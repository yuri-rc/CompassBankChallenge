package br.com.compass.core.voter.user;

import br.com.compass.core.usecase.user.input.CreateUserInput;
import br.com.compass.core.voter.Voter;

import java.time.LocalDate;

public class CreateUserVoter implements Voter<CreateUserInput> {

    @Override
    public void invoke(CreateUserInput useCaseInput) {
        if (useCaseInput == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        validateName(useCaseInput.name());
        validateBirthDate(useCaseInput.birthDate());
        validateCPF(useCaseInput.cpf());
        validatePhone(useCaseInput.phone());
        validatePassword(useCaseInput.password(), useCaseInput.confirmPassword());
    }

    private void validateName(String name) {
        if (name == null || name.trim().length() < 2) {
            throw new IllegalArgumentException("Name must have at least 2 characters.");
        }
    }

    private void validateBirthDate(String birthDate) {
        try {
            LocalDate date = LocalDate.parse(birthDate);
            if (date.isAfter(LocalDate.now())) {
                throw new IllegalArgumentException("Birth date cannot be in the future.");
            }
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid birth date format. Use yyyy-MM-dd.");
        }
    }

    private void validateCPF(String cpf) {
        if (cpf == null || cpf.length() != 11 || !cpf.matches("\\d+")) {
            throw new IllegalArgumentException("CPF must be 11 numeric characters.");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || phone.length() < 10 || phone.length() > 15 || !phone.matches("\\d+")) {
            throw new IllegalArgumentException("Phone must be between 10 and 15 numeric characters.");
        }
    }

    private void validatePassword(String password, String confirmPassword) {
        if (password == null || password.length() < 6) {
            throw new IllegalArgumentException("Password must have at least 6 characters.");
        }

        if (!password.equals(confirmPassword)) {
            throw new IllegalArgumentException("Password and confirm password must be equals.");
        }
    }
}