package br.com.compass.adapter.controller;

import br.com.compass.core.domain.transaction.Transaction;
import br.com.compass.core.usecase.transaction.GetTransactionsUseCase;
import br.com.compass.infra.repository.TransactionRepository;

import java.util.List;

public class TransactionController {
    public static void listTransactions(Integer accountId) {
        GetTransactionsUseCase getTransactionsUseCase = new GetTransactionsUseCase(new TransactionRepository());

        try {
            List<Transaction> transactionList = getTransactionsUseCase.execute(accountId);
            printTransactions(transactionList);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage() + "\nTry again.");
        }
    }

    public static void printTransactions(List<Transaction> transactions) {
        String headerFormat = "+-----------------------+--------------+---------------------------+----------------------+-----------------------+";
        String rowFormat = "| %-21s | %-12s | %-25s | %-20s | %-21s |%n";

        System.out.println(headerFormat);
        System.out.println("| Transaction Type      | Amount       | Date                      | Source Account ID    | Destination Account   |");
        System.out.println(headerFormat);

        for (Transaction transaction : transactions) {
            System.out.printf(
                    rowFormat,
                    transaction.getTransactionType(),
                    transaction.getFormatterAmount(),
                    transaction.getDate() != null ? transaction.getDate().toString() : "N/A",
                    transaction.getSourceAccountId() != null ? transaction.getSourceAccountId().toString() : "N/A",
                    transaction.getDestinationAccountId() != null ? transaction.getDestinationAccountId().toString() : "N/A"
            );
        }

        System.out.println(headerFormat);
    }

}
