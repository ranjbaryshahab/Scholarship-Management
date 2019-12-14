package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

@UseCase
public interface SubmitByUserUseCase {
    void submit(String username,String password,String role);
}
