package ir.maktab.java32.projects.scholarshipmanagement.core.share.log;

public interface DeleteLogUseCase {
    void deleteByUsername(String username);
    void deleteByScholarshipId(int scholarshipId);
}
