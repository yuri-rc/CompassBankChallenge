package br.com.compass.core.domain.account;

public class BalanceUpdated {
    private final Account account;
    private final String destinationAccountNumber;
    private final Double amount;
    private final String transactionType;

    public BalanceUpdated(Account account, String destinationAccountNumber, Double amount, String transactionType) {
        this.account = account;
        this.destinationAccountNumber = destinationAccountNumber;
        this.amount = amount;
        this.transactionType = transactionType;
    }

    public BalanceUpdated(Account account, Double amount, String transactionType) {
        this(account, null, amount, transactionType);
    }

    public Account getAccount() {
        return account;
    }

    public String getDestinationAccountNumber() {
        return destinationAccountNumber;
    }

    public Double getAmount() {
        return amount;
    }

    public String getTransactionType() {
        return transactionType;
    }
}

