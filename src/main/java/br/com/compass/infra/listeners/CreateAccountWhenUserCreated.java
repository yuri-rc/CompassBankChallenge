package br.com.compass.infra.listeners;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.domain.user.UserCreated;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.services.event.EventListener;

public class CreateAccountWhenUserCreated implements EventListener<UserCreated> {
    private final AccountRepositoryInterface accountRepository;

    public CreateAccountWhenUserCreated(AccountRepositoryInterface accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public void onEvent(UserCreated event) {
        Account account = new Account(
                event.getAccountType(),
                event.getUser().getId()
        );
        try {
            accountRepository.save(account);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
