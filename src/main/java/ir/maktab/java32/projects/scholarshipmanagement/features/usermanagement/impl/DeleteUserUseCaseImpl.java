package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.DeleteUserUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.FindUserUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteUserUseCaseImpl implements DeleteUserUseCase {
    @Override
    public void delete(String username) {
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "delete from user where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

    }
}
