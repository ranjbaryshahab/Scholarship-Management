package ir.maktab.java32.projects.scholarshipmanagement.features.report.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.usecases.ScholarshipReportByStudentUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.usecases.ScholarshipReportBySupervisorUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ScholarshipReportBySupervisorUseCaseImpl implements ScholarshipReportBySupervisorUseCase {
    @Override
    public Map<String, Integer> report() {
        Map<String, Integer> report = new HashMap<>();
        User user = AuthenticationService.getInstance().getLoginUser();
        if (user != null && user.getRole().equals("Supervisor")) {
            try {
                Connection connection = DatabaseConfig.getDatabaseConnection();
                String sql = "SELECT status,COUNT(*) FROM scholarship GROUP BY status";
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    report.put(resultSet.getString(1), resultSet.getInt(2));
                }
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        }
        return report;
    }
}
