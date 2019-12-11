package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class LoginUseCaseImpl implements LoginUseCase {
    public User login(String username, String password) {
        // get connection
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "select id, username, password, role from user where " +
                    " username = ? and password = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new User(
                  resultSet.getLong("id"),
                  resultSet.getString("username"),
                  resultSet.getString("password"),
                  resultSet.getString("role")
                );
                AuthenticationService.getInstance().setLoginUser(user);
                return user;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        // return result
        return null;
    }
}
