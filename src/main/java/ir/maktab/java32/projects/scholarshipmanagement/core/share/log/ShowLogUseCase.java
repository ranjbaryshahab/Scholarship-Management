package ir.maktab.java32.projects.scholarshipmanagement.core.share.log;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Log;

import java.util.List;

@UseCase
public interface ShowLogUseCase {
    List<Log> showLog();
    List<Log> showLogByScholarshipId(int scholarship_id);
}
