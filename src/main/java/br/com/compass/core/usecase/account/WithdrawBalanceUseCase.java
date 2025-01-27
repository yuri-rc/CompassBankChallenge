package br.com.compass.core.usecase.account;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.domain.account.BalanceUpdated;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.services.event.EventPublisher;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.account.input.WithdrawBalanceInput;
import br.com.compass.core.voter.account.WithdrawBalanceVoter;
import br.com.compass.infra.listeners.SaveTransactionWhenBalanceUpdated;
import br.com.compass.infra.repository.TransactionRepository;

public class WithdrawBalanceUseCase implements UseCase<WithdrawBalanceInput, Account> {
    private final AccountRepositoryInterface accountRepository;
    private final EventPublisher eventPublisher;

    public WithdrawBalanceUseCase(AccountRepositoryInterface accountRepository, EventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
        eventPublisher.addListener(new SaveTransactionWhenBalanceUpdated(new TransactionRepository(),accountRepository));
    }

    @Override
    public Account execute(WithdrawBalanceInput input) {
        new WithdrawBalanceVoter().invoke(input);
        try {
            Account account = accountRepository.withdrawBalance(input.account(), input.amount());
            eventPublisher.publish(new BalanceUpdated(input.account(), input.amount(), "WITHDRAW"));
            return account;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}