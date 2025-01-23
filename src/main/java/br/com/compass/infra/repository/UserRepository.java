package br.com.compass.infra.repository;

import br.com.compass.core.domain.User;
import br.com.compass.core.repository.UserRepositoryInterface;
import br.com.compass.infra.config.connection.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class UserRepository implements UserRepositoryInterface {
    @Override
    public void save(User user) throws Exception {
        try (Connection connection = DatabaseConnection.getConnection()) {
            String sql = "INSERT INTO BankUser (name, birth_date, cpf, phone, password) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, user.getName());
            statement.setDate(2, user.getBirthDate());
            statement.setString(3, user.getCpf());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getPassword());
            statement.executeUpdate();
        }
    }
}
