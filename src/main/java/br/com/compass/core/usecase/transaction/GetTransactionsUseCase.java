package br.com.compass.core.usecase.transaction;

import br.com.compass.core.domain.transaction.Transaction;
import br.com.compass.core.repository.TransactionRepositoryInterface;
import br.com.compass.core.usecase.UseCase;

import java.util.List;

public class GetTransactionsUseCase implements UseCase<Integer, List<Transaction>> {
    private final TransactionRepositoryInterface transactionRepository;

    public GetTransactionsUseCase(TransactionRepositoryInterface transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    @Override
    public List<Transaction> execute(Integer input) {
        try {
            return transactionRepository.getByAccountId(input);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
