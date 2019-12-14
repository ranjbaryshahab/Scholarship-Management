package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipBySupervisorUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectAllScholarshipBySupervisorUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RejectAllScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class RejectAllScholarshipBySupervisorUseCaseImpl implements RejectAllScholarshipBySupervisorUseCase {
    @Override
    public void rejectAll() {
        User user = AuthenticationService.getInstance().getLoginUser();
        LogRecordUseCase logRecordUseCase = new LogRecordUseCaseImpl();
        if (user != null && user.getRole().equals("Supervisor")) {
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                String sql = "update scholarship set status = 'RejectedBySupervisor' where status='RequestByStudent'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase =
                        new FindScholarshipBySupervisorUseCaseImpl();
                List<Scholarship> scholarshipList = findScholarshipBySupervisorUseCase.listScholarships();
                for (Scholarship scholarship:scholarshipList) {
                    logRecordUseCase.log("RejectedBySupervisor" , new Date(), scholarship.getId());
                }
                preparedStatement.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
