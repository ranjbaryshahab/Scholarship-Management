package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogRecordUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Log;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class LogRecordUseCaseImpl implements LogRecordUseCase {

    @Override
    public void log(String activity, Date date) {
        User user = AuthenticationService.getInstance().getLoginUser();
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "insert into log(username, date, activity) values (?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, String.valueOf(date));
            preparedStatement.setString(3, activity);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

