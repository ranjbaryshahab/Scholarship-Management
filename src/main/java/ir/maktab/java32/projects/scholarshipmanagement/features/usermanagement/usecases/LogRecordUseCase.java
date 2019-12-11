package ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases;

import ir.maktab.java32.projects.scholarshipmanagement.core.annotations.UseCase;

import java.util.Date;


@UseCase
public interface LogRecordUseCase {
    void log(String activity, Date date);
}
