package br.com.compass;

import br.com.compass.adapter.controller.AccountController;
import br.com.compass.adapter.controller.TransactionController;
import br.com.compass.adapter.controller.UserController;
import br.com.compass.core.domain.account.Account;

import java.util.Scanner;

public class App {
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        mainMenu(scanner);
        
        scanner.close();
        System.out.println("Application closed");
    }

    public static void mainMenu(Scanner scanner) {
        boolean running = true;

        while (running) {
            System.out.println("========= Main Menu =========");
            System.out.println("|| 1. Login                ||");
            System.out.println("|| 2. Account Opening      ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    Account account = AccountController.login(scanner);
                    if(account != null) {
                        System.out.println("Login successful.");
                        bankMenu(scanner, account);
                    }
                    break;
                case 2:
                    UserController.create(scanner);
                    System.out.println("Account Opening.");
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }

    public static void bankMenu(Scanner scanner, Account account) {
        boolean running = true;

        while (running) {
            System.out.println("========= Bank Menu =========");
            System.out.println("|| 1. Deposit              ||");
            System.out.println("|| 2. Withdraw             ||");
            System.out.println("|| 3. Check Balance        ||");
            System.out.println("|| 4. Transfer             ||");
            System.out.println("|| 5. Bank Statement       ||");
            System.out.println("|| 0. Exit                 ||");
            System.out.println("=============================");
            System.out.print("Choose an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    AccountController.addBalance(scanner, account);
                    System.out.println("You have " + account.getFormatterBalance() + ".");
                    break;
                case 2:
                    AccountController.withdrawBalance(scanner, account);
                    System.out.println("You have " + account.getFormatterBalance() + ".");
                    break;
                case 3:
                    System.out.println("You have " + account.getFormatterBalance() + ".");
                    break;
                case 4:
                    AccountController.transferBalance(scanner, account);
                    System.out.println("You have " + account.getFormatterBalance() + ".");
                    break;
                case 5:
                    TransactionController.listTransactions(account.getAccountId());
                    break;
                case 0:
                    System.out.println("Exiting...");
                    running = false;
                    return;
                default:
                    System.out.println("Invalid option! Please try again.");
            }
        }
    }
    
}
