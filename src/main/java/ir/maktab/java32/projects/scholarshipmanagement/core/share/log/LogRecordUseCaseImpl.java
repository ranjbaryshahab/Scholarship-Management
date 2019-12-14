package ir.maktab.java32.projects.scholarshipmanagement.core.share.log;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.FindScholarshipByStudentUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByStudentUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;

@Service
public class LogRecordUseCaseImpl implements LogRecordUseCase {

    @Override
    public void log(String action, Date date,int scholarship_id) {
        User user = AuthenticationService.getInstance().getLoginUser();
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "insert into log(username,scholarship_id,date, action) values (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setInt(2, scholarship_id);
            preparedStatement.setString(3, String.valueOf(date));
            preparedStatement.setString(4, action);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}

