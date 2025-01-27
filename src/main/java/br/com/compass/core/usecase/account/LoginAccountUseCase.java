package br.com.compass.core.usecase.account;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.domain.user.User;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.core.repository.UserRepositoryInterface;
import br.com.compass.core.usecase.UseCase;
import br.com.compass.core.usecase.account.input.LoginAccountInput;
import org.mindrot.jbcrypt.BCrypt;

public class LoginAccountUseCase implements UseCase<LoginAccountInput, Account> {
    private final UserRepositoryInterface userRepository;
    private final AccountRepositoryInterface accountRepository;

    public LoginAccountUseCase(UserRepositoryInterface userRepository, AccountRepositoryInterface accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public Account execute(LoginAccountInput input) {
        Account account;
        User user;
        try {
            account = accountRepository.getByAccountNumber(input.accountNumber());
            user = userRepository.getById(account.getUserId());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if(account == null) {
            throw new IllegalArgumentException("Account not found");
        }

        if(!BCrypt.checkpw(input.password(), user.getPassword())) {
            throw new IllegalArgumentException("Incorrect password");
        }
        return account;
    }
}
