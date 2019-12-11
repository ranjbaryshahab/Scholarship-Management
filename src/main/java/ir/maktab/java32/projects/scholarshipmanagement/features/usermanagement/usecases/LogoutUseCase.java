package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

@UseCase
public interface LogoutUseCase {
    User logout();
}
