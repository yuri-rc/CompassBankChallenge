package br.com.compass.core.voter.account;

import br.com.compass.core.usecase.account.input.AddBalanceInput;
import br.com.compass.core.voter.Voter;

import java.util.Objects;

public class AddBalanceVoter implements Voter<AddBalanceInput>  {
    @Override
    public void invoke(AddBalanceInput useCaseInput) {
        if (useCaseInput == null) {
            throw new IllegalArgumentException("Input cannot be null.");
        }

        if (Objects.equals(useCaseInput.account().getAccountType(), "Salary")) {
            throw new IllegalArgumentException("Salary account can't add balance.");
        }
    }
}
