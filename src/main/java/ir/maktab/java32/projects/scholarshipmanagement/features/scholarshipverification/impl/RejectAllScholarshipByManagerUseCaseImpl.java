package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByManagerUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectAllScholarshipByManagerUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectAllScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RejectAllScholarshipByManagerUseCaseImpl implements RejectAllScholarshipByManagerUseCase {
    @Override
    public void rejectAll() {
        User user = AuthenticationService.getInstance().getLoginUser();
        LogRecordUseCase logRecordUseCase = new LogRecordUseCaseImpl();
        if (user != null && user.getRole().equals("Manager")) {
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                String sql = "update scholarship set status = 'RejectedByManager' where status='AcceptedBySupervisor'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase =
                        new FindScholarshipByManagerUseCaseImpl();
                List<Scholarship> scholarshipList = findScholarshipByManagerUseCase.listScholarships();
                for (Scholarship scholarship:scholarshipList) {
                    logRecordUseCase.log("RejectedByManager" , new Date(), scholarship.getId());
                }
                preparedStatement.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
