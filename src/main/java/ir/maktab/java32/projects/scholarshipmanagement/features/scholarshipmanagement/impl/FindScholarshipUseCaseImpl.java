package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.usecases.FindScholarshipUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl.FindUserUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.FindUserUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FindScholarshipUseCaseImpl implements FindScholarshipUseCase {
    @Override
    public List<Scholarship> findAllScholarshipByUsername(String username) {
        Connection connection = null;
        List<Scholarship> result = new ArrayList<>();
        try {
            connection = DatabaseConfig.getDatabaseConnection();
            // query
            String sql = "select * from scholarship where username=?";
            // result
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            FindUserUseCase findUserUseCase = new FindUserUseCaseImpl();
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
                        findUserUseCase.findByUsername(username)
                );
                result.add(scholarship);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
