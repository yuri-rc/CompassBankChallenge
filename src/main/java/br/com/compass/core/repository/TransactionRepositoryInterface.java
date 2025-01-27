package br.com.compass.core.repository;

import br.com.compass.core.domain.transaction.Transaction;

import java.util.List;

public interface TransactionRepositoryInterface {
    void save(Transaction transaction) throws Exception;
    List<Transaction> getByAccountId(Integer id) throws Exception;
}
