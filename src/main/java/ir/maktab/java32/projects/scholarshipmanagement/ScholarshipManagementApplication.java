package ir.maktab.java32.projects.scholarshipmanagement;

import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.*;
import ir.maktab.java32.projects.scholarshipmanagement.model.Log;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class ScholarshipManagementApplication {
    static User user = null;
    static Scanner scanner = new Scanner(System.in);
    static String command = "";
    static LogRecordUseCase logRecordUseCase = new LogRecordUseCaseImpl();

    public static void main(String[] args) {

        while (!command.equals("exit")) {
            System.out.println("Please choose of menu and type it: ");
            if (user == null) {
                defaultMenu();
            } else if (user.getRole().equals("Supervisor")) {
                supervisorMenu();
            } else if (user.getRole().equals("Student")) {
                studentMenu();
            } else if (user.getRole().equals("Manager")) {
                managerMenu();
            } else if (user.getRole().equals("University")) {
                universityMenu();
            }
        }
    }

    public static String printDefaultMenu() {
        return """
                -----------------
                |    Sign in    |
                |    Sign up    |
                |    Exit       |
                -----------------
                """;
    }

    public static String printSupervisorMenu() {
        return """
                -------------------------------
                |     Show scholarship list   |
                |     Accept scholarship      |
                |     Reject scholarship      |
                |     Logout                  |
                -------------------------------
                """;
    }

    public static String printManagerMenu() {
        return """
                ---------------------------------
                |      Show scholarship list    |
                |      Accept scholarship       |
                |      Reject scholarship       |
                |      Logout                   |
                ---------------------------------
                """;
    }

    public static String printStudentMenu() {
        return """
                ----------------------------
                |     Request scholarship  |
                |     Show scholarship     |
                |     Logout               |
                ----------------------------
                 """;
    }

    public static String printUniversityMenu() {
        return """
                ----------------------------
                |     Show scholarship     |
                |     Show Log             |
                |     Logout               |
                ----------------------------
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
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                }
            }
            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
                logRecordUseCase.log("logout", new Date());
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
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                }
            }
            case "accept scholarship" -> {
                AcceptScholarshipByManagerUseCase acceptScholarshipByManagerUseCase =
                        new AcceptScholarshipByManagerUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipByManagerUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            case "reject scholarship" -> {
                RejectScholarshipByManagerUseCase rejectScholarshipByManagerUseCase =
                        new RejectScholarshipByManagerUseCaseImpl();
                System.out.println("Please enter the user id for reject: ");
                long id = scanner.nextLong();
                rejectScholarshipByManagerUseCase.rejected(id);
            }
            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                logRecordUseCase.log("logout", new Date());
                user = logoutUseCase.logout();
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
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                }
            }

            case "accept scholarship" -> {
                AcceptScholarshipBySupervisorUseCase acceptScholarshipBySupervisorUseCase
                        = new AcceptScholarshipBySupervisorUseCaseImpl();
                System.out.println("Scholarship Id: ");
                String scholarshipId = scanner.nextLine();
                acceptScholarshipBySupervisorUseCase.accept(Long.parseLong(scholarshipId));
                System.out.println("Done.");
            }
            case "reject scholarship" -> {
                RejectScholarshipBySupervisorUseCase rejectScholarshipBySupervisorUseCase =
                        new RejectScholarshipBySupervisorUseCaseImpl();
                System.out.println("Please enter the user id for reject: ");
                long id = scanner.nextLong();
                rejectScholarshipBySupervisorUseCase.rejected(id);
            }
            case "logout" -> {
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
                logRecordUseCase.log("logout", new Date());
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
                    logRecordUseCase.log("login", new Date());
                    System.out.println("\n\t[Login successful by " + user.getRole() + "]\n");
                }
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
            case "show scholarship"->{
                FindScholarshipByUniversityUseCase findScholarshipByUniversityUseCase =
                        new FindScholarshipByUniversityUseCaseImpl();
                List<Scholarship> scholarships = findScholarshipByUniversityUseCase
                        .listScholarships();
                for (Scholarship scholarship : scholarships) {
                    System.out.println(scholarship);
                }
            }
            case "show log"->{
                ShowLogUseCase showLogUseCase = new ShowLogUseCaseImpl();
                List<Log> logs = showLogUseCase.showLog();

                for(Log log : logs){
                    System.out.println(log);
                }
            }
            case "logout"->{
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
                logRecordUseCase.log("logout", new Date());
            }
        }
    }
}
