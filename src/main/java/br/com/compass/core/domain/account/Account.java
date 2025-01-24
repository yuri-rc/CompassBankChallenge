package br.com.compass.core.domain.account;

import java.text.NumberFormat;
import java.util.Locale;

public class Account {
    private String accountNumber;
    private final String accountType;
    private double balance;
    private final int userId;

    public Account(String accountType, int userId) {
        this.accountType = accountType;
        this.userId = userId;
    }

    public void setAccountNumber(String number) {
        accountNumber = number;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setBalance(double amount) {
        balance = amount;
    }

    public double getBalance() {
        return balance;
    }

    public int getUserId() {
        return userId;
    }

    public String getFormatterBalance() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormat.format(this.balance);
    }
}
