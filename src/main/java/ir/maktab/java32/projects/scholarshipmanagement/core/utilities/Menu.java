package ir.maktab.java32.projects.scholarshipmanagement.core.utilities;

import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.ShowLogUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.ShowLogUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.impl.ScholarshipReportByManagerUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.impl.ScholarshipReportByStudentUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.impl.ScholarshipReportBySupervisorUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.impl.ScholarshipReportByUniversityUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.usecases.ScholarshipReportByManagerUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.usecases.ScholarshipReportByStudentUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.usecases.ScholarshipReportBySupervisorUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.report.usecases.ScholarshipReportByUniversityUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LoginUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl.LogoutUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl.SubmitByUserUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LoginUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.LogoutUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.SubmitByUserUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.model.Log;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Menu {
    public static User user = null;
    public static Scanner scanner = new Scanner(System.in);
    public static String command = "";

    public static String printDefaultMenu() {
        return """
                +---------------+
                |    Sign in    |
                |    Sign up    |
                |    Exit       |
                +---------------+
                """;
    }

    public static String printSupervisorMenu() {
        return """
                +-----------------------------+
                |     Show scholarship list   |
                |     Accept scholarship      |
                |     Reject scholarship      |
                |     Report                  |
                |     Logout                  |
                +-----------------------------+
                """;
    }

    public static String printManagerMenu() {
        return """
                +-------------------------------+
                |      Show scholarship list    |
                |      Accept scholarship       |
                |      Reject scholarship       |
                |      Report                   |
                |      Logout                   |
                +-------------------------------+
                """;
    }

    public static String printStudentMenu() {
        return """
                +--------------------------+
                |     Request scholarship  |
                |     Show scholarship     |
                |     Report               |
                |     Logout               |
                +--------------------------+
                 """;
    }

    public static String printUniversityMenu() {
        return """
                +--------------------------+
                |     Show scholarship     |
                |     Accept scholarship   |
                |     Reject scholarship   |
                |     Report               |
                |     Logout               |
                +--------------------------+
                 """;
    }


    public static void studentMenu() {
        System.out.println(printStudentMenu());
        command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "request scholarship" -> {
                Scholarship scholarship = new Scholarship();
                System.out.println("Please enter your first name :");
                scholarship.setName(scanner.nextLine());
                System.out.println("Please enter your last name :");
                scholarship.setFamily(scanner.nextLine());
                System.out.println("Please enter your national code :");
                scholarship.setNationalCode(scanner.nextLine());
                System.out.println("Please enter your last field:");
                scholarship.setLastField(scanner.nextLine());
                System.out.println("Please enter your new field for apply:");
                scholarship.setApplyField(scanner.nextLine());
                System.out.println("Please enter your last degree:");
                scholarship.setLastDegree(scanner.nextLine());
                System.out.println("Please enter your new degree for apply:");
                scholarship.setApplyDegree(scanner.nextLine());
                System.out.println("Please enter your last uni:");
                scholarship.setLastUni(scanner.nextLine());
                System.out.println("Please enter your new uni for apply:");
                scholarship.setApplyUni(scanner.nextLine());
                System.out.println("Please enter your last score:");
                scholarship.setLastScore(Float.parseFloat(scanner.nextLine()));
                scholarship.setStatus("RequestedByStudent");
                System.out.println("Please enter your apply date:");
                scholarship.setApplyDate(scanner.nextLine());
                RequestScholarshipByStudentUseCase requestScholarshipByStudentUseCase =
                        new RequestScholarshipByStudentUseCaseImpl();
                requestScholarshipByStudentUseCase.request(scholarship);
            }
            case "show scholarship" -> {
                FindScholarshipByStudentUseCase findScholarshipByStudentUseCase =
                        new FindScholarshipByStudentUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByStudentUseCase
                        .listScholarships();
                ShowLogUseCase showLogUseCase = new ShowLogUseCaseImpl();
                System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                    System.out.println("Logs -> { ");
                    List<Log> logs = showLogUseCase.showLogByScholarshipId(scholarship.getId());
                    for (Log log : logs) {
                        System.out.println(log);
                    }
                    System.out.println("}");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
            }

            case "report" -> {
                ScholarshipReportByStudentUseCase scholarshipReportByStudentUseCase =
                        new ScholarshipReportByStudentUseCaseImpl();
                scholarshipReportByStudentUseCase.report().forEach((K,V)-> System.out.println("Status :" + K + ", Count:" + V));
            }
        }
    }

    public static void managerMenu() {
        System.out.println(printManagerMenu());
        command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "show scholarship list" -> {
                FindScholarshipByManagerUseCase findScholarshipByManagerUseCase =
                        new FindScholarshipByManagerUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByManagerUseCase
                        .listScholarships();
                ShowLogUseCase showLogUseCase = new ShowLogUseCaseImpl();
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                    System.out.println("Logs -> { ");
                    List<Log> logs = showLogUseCase.showLogByScholarshipId(scholarship.getId());
                    for (Log log : logs) {
                        System.out.println(log);
                    }
                    System.out.println("}");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }
            case "accept scholarship" -> {
                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase =
                        new AcceptScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByManagerUseCase.accept(Integer.parseInt(scholarshipId));
                System.out.println("Done.");
            }
            case "reject scholarship" -> {
                RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase =
                        new RejectScholarshipByManagerUseCaseImpl();
                System.out.println("Please enter the user id for reject: ");
                int id = scanner.nextInt();
                rejectScholarshipByManagerUseCase.rejected(id);
            }
            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
            }

            case "report" -> {
                ScholarshipReportByManagerUseCase scholarshipReportByManagerUseCase =
                        new ScholarshipReportByManagerUseCaseImpl();
                scholarshipReportByManagerUseCase.report().forEach((K,V)-> System.out.println("Status :" + K + ", Count:" + V));
            }
        }
    }

    public static void supervisorMenu() {
        System.out.println(printSupervisorMenu());
        command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "show scholarship list" -> {
                FindScholarshipBySupervisorUseCase findScholarshipBySupervisorUseCase
                        = new FindScholarshipBySupervisorUseCaseImpl();

                List<Scholarship> scholarships = findScholarshipBySupervisorUseCase
                        .listScholarships();
                ShowLogUseCase showLogUseCase = new ShowLogUseCaseImpl();
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                    System.out.println("Logs -> { ");
                    List<Log> logs = showLogUseCase.showLogByScholarshipId(scholarship.getId());
                    for (Log log : logs) {
                        System.out.println(log);
                    }
                    System.out.println("}");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }

            case "accept scholarship" -> {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Integer.parseInt(scholarshipId));
                System.out.println("Done.");
            }
            case "reject scholarship" -> {
                RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase =
                        new RejectScholarshipBySupervisorUseCaseImpl();
                System.out.println("Please enter the user id for reject: ");
                int id = scanner.nextInt();
                rejectScholarshipBySupervisorUseCase.rejected(id);
            }
            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
            }

            case "report" -> {
                ScholarshipReportBySupervisorUseCase scholarshipReportBySupervisorUseCase =
                        new ScholarshipReportBySupervisorUseCaseImpl();
                scholarshipReportBySupervisorUseCase.report().forEach((K,V)-> System.out.println("Status :" + K + ", Count:" + V));
            }
        }
    }

    public static void defaultMenu() {
        System.out.println(printDefaultMenu());
        command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "sign in" -> {
                System.out.println("Username : ");
                String username = scanner.nextLine();
                System.out.println("Password : ");
                String password = scanner.nextLine();
                LoginUseCase loginUseCase = new LoginUseCaseImpl();
                user = loginUseCase.login(username, password);
                if (user != null) {
                    System.out.println("\n\t[Login successful by " + user.getRole() + "]\n");
                } else
                    System.out.println("\tLogin Failed! Invalid Username or Password!");
            }
            case "sign up" -> {
                System.out.println("Please enter a username: ");
                String username = scanner.nextLine();
                System.out.println("Please enter a password: ");
                String password = scanner.nextLine();
                SubmitByUserUseCase submitByUserUseCase =
                        new SubmitByUserUseCaseImpl();
                submitByUserUseCase.submit(username, password, "Student");
            }
        }
    }

    public static void universityMenu() {
        System.out.println(printUniversityMenu());
        command = scanner.nextLine().toLowerCase();
        switch (command) {
            case "show scholarship" -> {
                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase =
                        new FindScholarshipByUniversityUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByUniversityUseCase
                        .listScholarships();
                ShowLogUseCase showLogUseCase = new ShowLogUseCaseImpl();
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                    System.out.println("Logs -> { ");
                    List<Log> logs = showLogUseCase.showLogByScholarshipId(scholarship.getId());
                    for (Log log : logs) {
                        System.out.println(log);
                    }
                    System.out.println("}");
                    System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------");
                }
            }

            case "accept scholarship" -> {
                AcceptScholarshipByUniversityUseCase acceptScholarshipByUniversityUseCase
                        = new AcceptScholarshipByUniversityUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByUniversityUseCase.accept(Integer.parseInt(scholarshipId));
                System.out.println("Done.");
            }
            case "reject scholarship" -> {
                RejectScholarshipByUniversityUseCase rejectScholarshipByUniversityUseCase =
                        new RejectScholarshipByUniversityUseCaseImpl();
                System.out.println("Please enter the user id for reject: ");
                int id = scanner.nextInt();
                rejectScholarshipByUniversityUseCase.rejected(id);
            }

            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
            }

            case "report" -> {
                ScholarshipReportByUniversityUseCase scholarshipReportByUniversityUseCase =
                        new ScholarshipReportByUniversityUseCaseImpl();
                scholarshipReportByUniversityUseCase.report().forEach((K,V)-> System.out.println("Status :" + K + ", Count:" + V));
            }
        }

    }
}
