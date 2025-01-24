package br.com.compass.infra.repository;

import br.com.compass.core.domain.account.Account;
import br.com.compass.core.repository.AccountRepositoryInterface;
import br.com.compass.infra.config.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Random;

public class AccountRepository implements AccountRepositoryInterface {
    @Override
    public void save(Account account) throws Exception{
        try (Connection connection = DatabaseConnection.getConnection()) {
            String accountNumber = generateAccountNumber();

            String sql = "INSERT INTO Account (account_number, account_type, balance, user_id) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, accountNumber);
            statement.setString(2, account.getAccountType());
            statement.setDouble(3, 0.00);
            statement.setInt(4, account.getUserId());
            statement.executeUpdate();
        }
    }

    @Override
    public Account get(Integer userId) throws Exception {
        String sql = "SELECT * FROM Account WHERE user_id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setInt(1, userId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Account account = new Account(
                            resultSet.getString("account_type"),
                            resultSet.getInt("user_id")
                    );
                    account.setAccountNumber(resultSet.getString("account_number"));
                    account.setBalance(resultSet.getDouble("balance"));
                    return account;
                }
            }
        }
        return null;
    }

    private String generateAccountNumber() {
        Random random = new Random();
        return "ACC" + (100000 + random.nextInt(900000));
    }
}