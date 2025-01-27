package br.com.compass.infra.listeners;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.domain.account.BalanceUpdated;
import br.com.compass.core.domain.transaction.Transaction;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.repository.TransactionRepositoryInterface;
import br.com.compass.core.services.event.EventListener;

import java.sql.Timestamp;

public class SaveTransactionWhenBalanceUpdated implements EventListener<BalanceUpdated> {
    private final TransactionRepositoryInterface transactionRepository;
    private final AccountRepositoryInterface accountRepository;

    public SaveTransactionWhenBalanceUpdated(
            TransactionRepositoryInterface transactionRepository,
            AccountRepositoryInterface accountRepository
    ) {
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public void onEvent(BalanceUpdated event) {

        try {
            Account destinationAccount = accountRepository.getByAccountNumber(event.getDestinationAccountNumber());
            Integer destinationAccountId = (destinationAccount != null) ? destinationAccount.getAccountId() : null;
            if(destinationAccount != null) {
                accountRepository.addBalance(destinationAccount, event.getAmount());
            }

            Transaction transaction = new Transaction(
                    event.getTransactionType(),
                    event.getAmount(),
                    event.getAccount().getAccountId(),
                    destinationAccountId
            );
            transaction.setDate(new Timestamp(System.currentTimeMillis()));
            transactionRepository.save(transaction);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}