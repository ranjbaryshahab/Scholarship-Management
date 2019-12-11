package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.SubmitByUserUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class SubmitByUserUseCaseImpl implements SubmitByUserUseCase {

    @Override
    public void submit(String username, String password, String role) {
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "insert into user(username, password, role) values(?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, role);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
