package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.ShowLogUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Log;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ShowLogUseCaseImpl implements ShowLogUseCase {
    @Override
    public List<Log> showLog() {
        User user = AuthenticationService.getInstance().getLoginUser();
        List<Log> result = new ArrayList<>();
        if (user != null && user.getRole().equals("University")) {
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                String sql = "select * from log";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery(sql);
                while (resultSet.next()) {
                    User user1 = new User();
                    user1.setUsername(resultSet.getString("username"));
                    Log log = new Log(
                            resultSet.getLong("id"),
                            user1,
                            resultSet.getString("date"),
                            resultSet.getString("activity")
                    );
                    result.add(log);
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
