package br.com.compass.core.voter.account;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.usecase.account.input.TransfereBalanceInput;
import br.com.compass.core.voter.Voter;
import br.com.compass.infra.repository.AccountRepository;

import java.util.Objects;

public class TransferBalanceVoter implements Voter<TransfereBalanceInput>  {
    @Override
    public void invoke(TransfereBalanceInput useCaseInput) {
        if (useCaseInput == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        if(Objects.equals(useCaseInput.account().getAccountNumber(), useCaseInput.destinationAccountNumber())) {
            throw new IllegalArgumentException("You can't transfer it to yourself");
        }

        Account account;
        try {
            account = new AccountRepository().getByAccountNumber(useCaseInput.destinationAccountNumber());

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if(account == null) {
            throw new IllegalArgumentException("Acount not found.");
        }
    }
}
