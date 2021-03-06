package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.FindScholarshipByStudentUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Service
public class FindScholarshipByStudentUseCaseImpl implements FindScholarshipByStudentUseCase {
    @Override
    public List<Scholarship> listScholarships() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList<>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query
                    String sql = "select * from scholarship where username=?";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, loginUser.getUsername());
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

    @Override
    public Scholarship findById() {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        Scholarship scholarship =null;
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query
                    String sql = "select * from scholarship where username=?";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                    preparedStatement.setString(1, loginUser.getUsername());
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        scholarship = new Scholarship(
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

                    }
                } catch (ClassNotFoundException | SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return scholarship;
    }
}
