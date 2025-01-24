package br.com.compass.core.usecase.account;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.usecase.UseCase;

public class GetAccountUseCase implements UseCase<Integer, Account> {
    private final AccountRepositoryInterface accountRepository;

    public GetAccountUseCase(AccountRepositoryInterface accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(Integer input) {
        try {
            return accountRepository.get(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
