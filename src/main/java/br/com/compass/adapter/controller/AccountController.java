package br.com.compass.adapter.controller;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.services.event.EventPublisher;
import br.com.compass.core.usecase.account.AddBalanceUserCase;
import br.com.compass.core.usecase.account.TransferBalanceUseCase;
import br.com.compass.core.usecase.account.WithdrawBalanceUseCase;
import br.com.compass.core.usecase.account.input.AddBalanceInput;
import br.com.compass.core.usecase.account.input.TransfereBalanceInput;
import br.com.compass.core.usecase.account.input.WithdrawBalanceInput;
import br.com.compass.core.usecase.account.LoginAccountUseCase;
import br.com.compass.core.usecase.account.input.LoginAccountInput;
import br.com.compass.infra.repository.AccountRepository;
import br.com.compass.infra.repository.UserRepository;

import java.util.Scanner;

public class AccountController {
    public static Account login(Scanner scanner){
        System.out.print("Enter your account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter account password: ");
        String password = scanner.nextLine();

        LoginAccountUseCase loginAccountUseCase = new LoginAccountUseCase(new UserRepository(), new AccountRepository());
        try {
            return loginAccountUseCase.execute(new LoginAccountInput(
                    accountNumber,
                    password
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
        return null;
    }

    public static void addBalance(Scanner scanner, Account account) {
        System.out.print("Enter the amount to deposit: ");
        String amount = scanner.nextLine();

        AddBalanceUserCase addBalanceUserCase = new AddBalanceUserCase(new AccountRepository(),  new EventPublisher());

        try {
            addBalanceUserCase.execute(new AddBalanceInput(
                    Double.parseDouble(amount),
                    account
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
    }


    public static void withdrawBalance(Scanner scanner, Account account) {
        System.out.print("Enter the amount to withdraw: ");
        String amount = scanner.nextLine();

        WithdrawBalanceUseCase withdraBalanceUseCase = new WithdrawBalanceUseCase(new AccountRepository(), new EventPublisher());

        try {
            withdraBalanceUseCase.execute(new WithdrawBalanceInput(
                    Double.parseDouble(amount),
                    account
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
    }

    public static void transferBalance(Scanner scanner, Account account) {
        System.out.print("Enter the amount to transfer: ");
        String amount = scanner.nextLine();
        System.out.print("Enter the account number to transfer: ");
        String accountNumber = scanner.nextLine();

        TransferBalanceUseCase transferBalanceUseCase = new TransferBalanceUseCase(new AccountRepository(), new EventPublisher());

        try {
            transferBalanceUseCase.execute(new TransfereBalanceInput(
                    Double.parseDouble(amount),
                    account,
                    accountNumber
            ));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
    }
}