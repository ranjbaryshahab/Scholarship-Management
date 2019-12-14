package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

@UseCase
public interface AcceptScholarshipByManagerUseCase {
    void accept(int scholarshipId);
}
