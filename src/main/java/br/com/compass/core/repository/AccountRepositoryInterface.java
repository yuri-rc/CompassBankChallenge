package br.com.compass.core.repository;

import br.com.compass.core.domain.account.Account;

public interface AccountRepositoryInterface {
    void save(Account account) throws Exception;
    Account get(Integer userId) throws Exception;
}
