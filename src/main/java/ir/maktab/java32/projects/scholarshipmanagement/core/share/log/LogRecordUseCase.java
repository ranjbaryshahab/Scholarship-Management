package ir.maktab.java32.projects.scholarshipmanagement.core.share.log;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

import java.util.Date;


@UseCase
public interface LogRecordUseCase {
    void log(String action, Date date,int scholarship_id);
}
