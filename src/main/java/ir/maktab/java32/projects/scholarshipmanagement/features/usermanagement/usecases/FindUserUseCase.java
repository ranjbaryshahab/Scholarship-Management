package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;

public interface FindUserUseCase {
    User findByUsername(String username);
    List<User> findByRole(String role);
}
