package br.com.compass.core.repository;

import br.com.compass.core.domain.account.Account;

public interface AccountRepositoryInterface {
    void save(Account account) throws Exception;
    Account getByAccountNumber(String accountNumber) throws Exception;
    Account addBalance(Account account, Double amount) throws Exception;
    Account withdrawBalance(Account account, Double amount) throws Exception;
}
