package br.com.compass.core.usecase.account;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.domain.account.BalanceUpdated;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.services.event.EventPublisher;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.account.input.AddBalanceInput;
import br.com.compass.core.voter.account.AddBalanceVoter;
import br.com.compass.infra.listeners.SaveTransactionWhenBalanceUpdated;
import br.com.compass.infra.repository.TransactionRepository;

public class AddBalanceUserCase implements UseCase<AddBalanceInput, Account> {
    private final AccountRepositoryInterface accountRepository;
    private final EventPublisher eventPublisher;

    public AddBalanceUserCase(AccountRepositoryInterface accountRepository, EventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
        eventPublisher.addListener(new SaveTransactionWhenBalanceUpdated(new TransactionRepository(),accountRepository));
    }

    @Override
    public Account execute(AddBalanceInput input) {
        new AddBalanceVoter().invoke(input);
        try {
            Account account = accountRepository.addBalance(input.account(), input.amount());
            eventPublisher.publish(new BalanceUpdated(input.account(), input.amount(), "DEPOSIT"));
            return account;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}