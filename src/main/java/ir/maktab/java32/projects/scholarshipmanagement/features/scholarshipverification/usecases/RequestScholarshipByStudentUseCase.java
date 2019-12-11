package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;

@UseCase
public interface RequestScholarshipByStudentUseCase {
    void request(Scholarship scholarship);
}
