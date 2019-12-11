package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.Service;
import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.RequestScholarshipByStudentUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RequestScholarshipByStudentUseCaseImpl implements RequestScholarshipByStudentUseCase {
    @Override
    public void request(Scholarship scholarship) {
        User loginUser = AuthenticationService.getInstance().getLoginUser();
        List<Scholarship> result = new ArrayList<>();
        if (loginUser != null) {
            if (loginUser.getRole().equals("Student")) {
                // connection
                Connection connection = null;
                try {
                    connection = DatabaseConfig.getDatabaseConnection();
                    // query
                    String sql = "insert into scholarship(status, name, family, nationalCode, lastUni, lastDegree, lastField, lastScore, applyUni, applyDegree, applyField, applyDate,username) values (?,?,?,?,?,?,?,?,?,?,?,?,?)";
                    // result
                    PreparedStatement preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, scholarship.getStatus());
                    preparedStatement.setString(2, scholarship.getName());
                    preparedStatement.setString(3, scholarship.getFamily());
                    preparedStatement.setString(4, scholarship.getNationalCode());
                    preparedStatement.setString(5, scholarship.getLastUni());
                    preparedStatement.setString(6, scholarship.getLastDegree());
                    preparedStatement.setString(7, scholarship.getLastField());
                    preparedStatement.setFloat(8, scholarship.getLastScore());
                    preparedStatement.setString(9, scholarship.getApplyUni());
                    preparedStatement.setString(10, scholarship.getApplyDegree());
                    preparedStatement.setString(11, scholarship.getApplyField());
                    preparedStatement.setString(12, scholarship.getApplyDate());
                    preparedStatement.setString(13, loginUser.getUsername());
                    preparedStatement.executeUpdate();
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
