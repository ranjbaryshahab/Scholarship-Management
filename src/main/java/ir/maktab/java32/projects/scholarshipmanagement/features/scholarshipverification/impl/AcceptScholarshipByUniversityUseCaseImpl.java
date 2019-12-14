package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipByManagerUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class AcceptScholarshipByUniversityUseCaseImpl implements AcceptScholarshipByUniversityUseCase {
    private LogRecordUseCase logRecordUseCase = new LogRecordUseCaseImpl();

    @Override
    public void accept(int scholarshipId) {
        User user = AuthenticationService.getInstance().getLoginUser();

        if (user != null && user.getRole().equals("Manager")) {

            // connection
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                // sql
                String sql = "update scholarship set status = 'AcceptedByUniversity' " +
                        "where id = ?";
                // execute
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, scholarshipId);
                preparedStatement.executeUpdate();
                logRecordUseCase.log("AcceptedByManager" , new Date(), scholarshipId);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
