package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.LogRecordUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptAllScholarshipByManagerUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.AcceptAllScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipBySupervisorUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

@Service
public class AcceptAllScholarshipByUniversityUseCaseImpl implements AcceptAllScholarshipByUniversityUseCase {
    @Override
    public void acceptAll() {
        User user = AuthenticationService.getInstance().getLoginUser();
        LogRecordUseCase logRecordUseCase = new LogRecordUseCaseImpl();
        if (user != null && user.getRole().equals("University")) {

            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                String sql = "update scholarship set status = 'AcceptedByUniversity' where status='AcceptedByManager'";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase =
                        new FindScholarshipByUniversityUseCaseImpl();
                List<Scholarship> scholarshipList = findScholarshipByUniversityUseCase.listScholarships();
                for (Scholarship scholarship:scholarshipList) {
                    logRecordUseCase.log("AcceptedByUniversity" , new Date(), scholarship.getId());
                }
                preparedStatement.executeUpdate();
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
