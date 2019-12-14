package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

@UseCase
public interface AcceptScholarshipBySupervisorUseCase {
    void accept(int scholarshipId);
}
