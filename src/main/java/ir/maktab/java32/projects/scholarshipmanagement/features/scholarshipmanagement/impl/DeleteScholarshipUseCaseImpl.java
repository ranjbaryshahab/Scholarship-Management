package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.impl;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.usecases.DeleteScholarshipUseCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteScholarshipUseCaseImpl implements DeleteScholarshipUseCase {
    @Override
    public void deleteByScholarshipId(int id) {
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "delete from scholarship where id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByUsername(String username) {
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "delete from scholarship where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
