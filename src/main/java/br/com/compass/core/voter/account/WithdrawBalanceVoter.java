package br.com.compass.core.voter.account;

import br.com.compass.core.usecase.account.input.WithdrawBalanceInput;
import br.com.compass.core.voter.Voter;

import java.util.Objects;

public class WithdrawBalanceVoter implements Voter<WithdrawBalanceInput>  {
    @Override
    public void invoke(WithdrawBalanceInput useCaseInput) {
        if (useCaseInput == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        if (Objects.equals(useCaseInput.account().getAccountType(), "Salary")) {
            throw new IllegalArgumentException("Salary account can't withdraw balance.");
        }
    }
}
