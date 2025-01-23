package br.com.compass.adapter.controller;

import br.com.compass.core.usecase.user.CreateUserUseCase;
import br.com.compass.core.usecase.user.input.CreateUserInput;
import br.com.compass.infra.repository.UserRepository;

import java.util.Scanner;

public class UserController {
    public static void create(Scanner scanner){
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        System.out.print("Enter your birth date (YYYY-MM-DD): ");
        String birthDate = scanner.nextLine();
        System.out.print("Enter your CPF: ");
        String cpf = scanner.nextLine();
        System.out.print("Enter your phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter account password: ");
        String password = scanner.nextLine();
        System.out.print("Confirm account password: ");
        String confirmPassword = scanner.nextLine();

        CreateUserUseCase createAccountUseCase = new CreateUserUseCase(new UserRepository());
        try {
            createAccountUseCase.execute(new CreateUserInput(
                    name,
                    birthDate,
                    cpf,
                    phone,
                    password,
                    confirmPassword
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
    }
}
