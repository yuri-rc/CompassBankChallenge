package br.com.compass.adapter.controller;

import br.com.compass.core.services.event.EventPublisher;
import br.com.compass.core.usecase.user.CreateUserUseCase;
import br.com.compass.core.usecase.user.input.CreateUserInput;
import br.com.compass.infra.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
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
        System.out.print("Enter account type: ");

        try {
            List<String> accountTypes = chooseAccountTypes(scanner);
            CreateUserUseCase createUserUseCase = new CreateUserUseCase(new UserRepository(), new EventPublisher());

            createUserUseCase.execute(new CreateUserInput(
                    name,
                    birthDate,
                    cpf,
                    phone,
                    password,
                    confirmPassword,
                    accountTypes
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
    }

    private static List<String> chooseAccountTypes(Scanner scanner) {
        String[] accountOptions = {"Checking", "Salary", "Savings"};
        List<String> selectedAccounts = new ArrayList<>();

        System.out.println("Choose up to 3 account types:");
        for (int i = 0; i < accountOptions.length; i++) {
            System.out.println((i + 1) + ". " + accountOptions[i]);
        }

        while (selectedAccounts.size() < 3) {
            System.out.print("Enter the number of the account you want to add (or '0' to finish): ");
            String input = scanner.nextLine();

            try {
                int choice = Integer.parseInt(input);

                if (choice == 0) {
                    break;
                }

                if (choice < 1 || choice > accountOptions.length) {
                    System.out.println("Invalid option. Try again.");
                    continue;
                }

                String selectedAccount = accountOptions[choice - 1];

                if (selectedAccounts.contains(selectedAccount)) {
                    System.out.println("Account already selected. Choose another one.");
                } else {
                    selectedAccounts.add(selectedAccount);
                    System.out.println("Account added: " + selectedAccount);
                }

            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        if (selectedAccounts.isEmpty()) {
            System.out.println("No accounts selected. Operation canceled.");
            throw new IllegalArgumentException("No accounts were chosen.");
        }

        return selectedAccounts;
    }
}
