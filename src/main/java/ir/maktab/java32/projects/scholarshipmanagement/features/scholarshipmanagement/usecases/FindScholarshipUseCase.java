package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;

import java.util.List;

public interface FindScholarshipUseCase {
    List<Scholarship> findAllScholarshipByUsername(String username);
}
