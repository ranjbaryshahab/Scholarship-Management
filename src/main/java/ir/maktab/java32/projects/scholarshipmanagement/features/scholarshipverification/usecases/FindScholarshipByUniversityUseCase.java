package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;

import java.util.List;

@UseCase
public interface FindScholarshipByUniversityUseCase {
    List<Scholarship> listScholarships();
}
