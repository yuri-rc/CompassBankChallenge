package br.com.compass.infra.repository;

import br.com.compass.core.domain.transaction.Transaction;
import br.com.compass.core.repository.TransactionRepositoryInterface;
import br.com.compass.infra.config.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class TransactionRepository implements TransactionRepositoryInterface {
    @Override
    public void save(Transaction transaction) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO Transaction (transaction_type, amount, date, source_account_id, destination_account_id) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

            statement.setString(1, transaction.getTransactionType());
            statement.setDouble(2, transaction.getAmount());
            statement.setTimestamp(3, transaction.getDate());
            statement.setObject(4, transaction.getSourceAccountId());
            statement.setObject(5, transaction.getDestinationAccountId());

            statement.executeUpdate();
        }
    }

    @Override
    public List<Transaction> getByAccountId(Integer id) throws Exception {
        String sql = "SELECT * FROM Transaction WHERE source_account_id = ? ORDER BY date DESC";

        List<Transaction> transactions = new ArrayList<>();

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Transaction transaction = new Transaction(
                            resultSet.getString("transaction_type"),
                            resultSet.getDouble("amount"),
                            resultSet.getInt("source_account_id"),
                            resultSet.getInt("destination_account_id")
                    );
                    transaction.setDate(resultSet.getTimestamp("date"));
                    transaction.setId(resultSet.getInt("id"));
                    transactions.add(transaction);
                }
            }
        }
        return transactions;
    }

}
