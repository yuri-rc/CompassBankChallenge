package br.com.compass.core.domain.transaction;

import java.sql.Timestamp;
import java.text.NumberFormat;
import java.util.Locale;

public class Transaction {
    private Integer id;
    private final String transactionType;
    private final Double amount;
    private Timestamp date;
    private final Integer sourceAccountId;
    private final Integer destinationAccountId;

    public Transaction(String transactionType, Double amount, Integer sourceAccountId, Integer destinationAccountId) {
        this.transactionType = transactionType;
        this.amount = amount;
        this.sourceAccountId = sourceAccountId;
        this.destinationAccountId = destinationAccountId;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setDate(Timestamp timestamp) {
        date = timestamp;
    }

    public Timestamp getDate() {
        return date;
    }

    public Integer getSourceAccountId() {
        return sourceAccountId;
    }

    public Integer getDestinationAccountId() {
        return destinationAccountId;
    }

    public String getFormatterAmount() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormat.format(this.amount);
    }
}