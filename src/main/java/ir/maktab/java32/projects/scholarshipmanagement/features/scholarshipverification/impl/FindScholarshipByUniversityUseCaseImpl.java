package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindScholarshipByUniversityUseCaseImpl implements FindScholarshipByUniversityUseCase {
    @Override
    public List<Scholarship> listScholarships() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList<>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("University")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query
                    String sql = "select * from scholarship where status = 'AcceptedByManager' ";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Scholarship scholarship = new Scholarship(
                                resultSet.getInt("id"),
                                resultSet.getString("status"),
                                resultSet.getString("name"),
                                resultSet.getString("family"),
                                resultSet.getString("nationalCode"),
                                resultSet.getString("lastUni"),
                                resultSet.getString("lastDegree"),
                                resultSet.getString("lastField"),
                                resultSet.getFloat("lastScore"),
                                resultSet.getString("applyUni"),
                                resultSet.getString("applyDegree"),
                                resultSet.getString("applyField"),
                                resultSet.getString("applyDate"),
                                loginUser
                        );
                        result.add(scholarship);
                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }
}
