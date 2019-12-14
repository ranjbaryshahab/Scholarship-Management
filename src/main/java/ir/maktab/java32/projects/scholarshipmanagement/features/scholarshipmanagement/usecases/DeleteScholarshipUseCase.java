package ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.usecases;

public interface DeleteScholarshipUseCase {
    void deleteByScholarshipId(int id);
    void deleteByUsername(String username);
}
