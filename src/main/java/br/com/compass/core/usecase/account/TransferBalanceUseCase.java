package br.com.compass.core.usecase.account;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.domain.account.BalanceUpdated;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.services.event.EventPublisher;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.account.input.TransfereBalanceInput;
import br.com.compass.core.voter.account.TransferBalanceVoter;
import br.com.compass.infra.listeners.SaveTransactionWhenBalanceUpdated;
import br.com.compass.infra.repository.TransactionRepository;

public class TransferBalanceUseCase implements UseCase<TransfereBalanceInput, Account> {
    private final AccountRepositoryInterface accountRepository;
    private final EventPublisher eventPublisher;

    public TransferBalanceUseCase(AccountRepositoryInterface accountRepository, EventPublisher eventPublisher) {
        this.accountRepository = accountRepository;
        this.eventPublisher = eventPublisher;
        eventPublisher.addListener(new SaveTransactionWhenBalanceUpdated(new TransactionRepository(),accountRepository));
    }

    @Override
    public Account execute(TransfereBalanceInput input) {
        new TransferBalanceVoter().invoke(input);
        try {
            Account account = accountRepository.withdrawBalance(input.account(), input.amount());
            eventPublisher.publish(new BalanceUpdated(
                    input.account(),
                    input.destinationAccountNumber(),
                    input.amount(),
                    "TRANSFER"
            ));
            return account;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}