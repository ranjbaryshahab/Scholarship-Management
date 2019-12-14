package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectScholarshipBySupervisorUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

@Service
public class RejectScholarshipBySupervisorUseCaseImpl implements RejectScholarshipBySupervisorUseCase {
    @Override
    public void rejected(int scholarshipId) {
        User user = AuthenticationService.getInstance().getLoginUser();
        LogRecordUseCase logRecordUseCase = new LogRecordUseCaseImpl();
        if (user != null && user.getRole().equals("Manager")) {

            // connection
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                // sql
                String sql = "update scholarship set status = 'RejectedBySupervisor' " +
                        "where id = ?";
                // execute
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.setLong(1, scholarshipId);
                preparedStatement.executeUpdate();
                logRecordUseCase.log("RejectedBySupervisor" , new Date(), scholarshipId);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
