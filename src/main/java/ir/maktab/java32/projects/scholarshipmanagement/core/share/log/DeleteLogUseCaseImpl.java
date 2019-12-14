package ir.maktab.java32.projects.scholarshipmanagement.core.share.log;

import ir.maktab.java32.projects.scholarshipmanagement.core.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteLogUseCaseImpl implements DeleteLogUseCase {
    @Override
    public void deleteByUsername(String username) {
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "delete from log where username=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteByScholarshipId(int scholarshipId) {
        try {
            Connection connection = DatabaseConfig.getDatabaseConnection();
            String sql = "delete from log where scholarship_id=?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, scholarshipId);
            preparedStatement.executeUpdate();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
