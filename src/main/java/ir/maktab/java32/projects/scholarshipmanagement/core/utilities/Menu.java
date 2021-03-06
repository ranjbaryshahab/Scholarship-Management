package ir.maktab.java32.projects.scholarshipmanagement.core.utilities;

import ir.maktab.java32.projects.scholarshipmanagement.core.share.AuthenticationService;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.DeleteLogUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.core.share.log.DeleteLogUseCaseImpl;
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
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.impl.DeleteScholarshipUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.impl.FindScholarshipUseCaseImpl;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.usecases.DeleteScholarshipUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipmanagement.usecases.FindScholarshipUseCase;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.impl.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.scholarshipverification.usecases.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.impl.*;
import ir.maktab.java32.projects.scholarshipmanagement.features.usermanagement.usecases.*;
import ir.maktab.java32.projects.scholarshipmanagement.model.Log;
import ir.maktab.java32.projects.scholarshipmanagement.model.Scholarship;
import ir.maktab.java32.projects.scholarshipmanagement.model.User;

import java.util.List;
import java.util.Scanner;

@SuppressWarnings("Duplicates")
public class Menu {
    public static User user = null;
    public static Scanner scanner = new Scanner(System.in);
    public static Scanner optionScanner = new Scanner(System.in);
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
                |     Accept All              |
                |     Reject All              |
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
                |      Accept All               |
                |      Reject All               |
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
                |     Delete scholarship   |
                |     Report               |
                |     Delete account       |
                |     Logout               |
                +--------------------------+
                 """;
    }

    public static String printUniversityMenu() {
        return """
                +--------------------------------+
                |     Show scholarship           |
                |     Accept scholarship         |
                |     Reject scholarship         |
                |     Accept All                 |
                |     Reject All                 |
                |     Report                     |
                |     Add account                |
                |     Show account group by role |
                |     Delete account             |
                |     Logout                     |
                +--------------------------------+
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
                scholarshipReportByStudentUseCase.report().forEach((K, V) -> System.out.println("Status :" + K + ", Count:" + V));
            }

            case "delete scholarship" -> {
                System.out.println("Scholarship Id: ");
                int scholarshipId = scanner.nextInt();
                DeleteScholarshipUseCase deleteScholarshipUseCase =
                        new DeleteScholarshipUseCaseImpl();
                System.out.println("WARNING !!! \n Deleting a scholarship eliminates all of his information including logs!");
                System.out.println("Do you delete? (type yes or no)");
                String option = optionScanner.nextLine().toLowerCase();
                if (option.equals("yes")) {
                    deleteScholarshipUseCase.deleteByScholarshipId(scholarshipId);
                } else if (option.equals("no")) {
                    System.out.println(printUniversityMenu());
                }
            }

            case "delete account" -> {
                System.out.println("WARNING !!! \n Deleting a user eliminates all of his information including logs and scholarship!");
                System.out.println("Do you delete? (type yes or no)");
                String option = optionScanner.nextLine().toLowerCase();
                if (option.equals("yes")) {
                    String username = AuthenticationService.getInstance().getLoginUser().getUsername();
                    DeleteUserUseCase deleteUserUseCase =
                            new DeleteUserUseCaseImpl();
                    DeleteScholarshipUseCase deleteScholarshipUseCase =
                            new DeleteScholarshipUseCaseImpl();
                    DeleteLogUseCase deleteLogUseCase =
                            new DeleteLogUseCaseImpl();

                    FindScholarshipUseCase findScholarshipUseCase =
                            new FindScholarshipUseCaseImpl();
                    List<Scholarship> scholarshipList = findScholarshipUseCase.findAllScholarshipByUsername(username);

                    for (Scholarship scholarship : scholarshipList) {
                        deleteLogUseCase.deleteByScholarshipId(scholarship.getId());
                    }

                    for (Scholarship scholarship : scholarshipList) {
                        deleteScholarshipUseCase.deleteByScholarshipId(scholarship.getId());
                    }

                    deleteUserUseCase.delete(username);

                    LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                    user = logoutUseCase.logout();

                } else if (option.equals("no")) {
                    System.out.println(printUniversityMenu());
                }
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
                scholarshipReportByManagerUseCase.report().forEach((K, V) -> System.out.println("Status :" + K + ", Count:" + V));
            }

            case "accept all" -> {
                AcceptAllScholarshipByManagerUseCase acceptAllScholarshipByManagerUseCase =
                        new AcceptAllScholarshipByManagerUseCaseImpl();
                acceptAllScholarshipByManagerUseCase.acceptAll();
            }
            case "reject all" -> {
                RejectAllScholarshipByManagerUseCase rejectAllScholarshipByManagerUseCase =
                        new RejectAllScholarshipByManagerUseCaseImpl();
                rejectAllScholarshipByManagerUseCase.rejectAll();
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
                scholarshipReportBySupervisorUseCase.report().forEach((K, V) -> System.out.println("Status :" + K + ", Count:" + V));
            }

            case "accept all" -> {
                AcceptAllScholarshipBySupervisorUseCase acceptAllScholarshipBySupervisorUseCase =
                        new AcceptAllScholarshipBySupervisorUseCaseImpl();
                acceptAllScholarshipBySupervisorUseCase.acceptAll();
            }
            case "reject all" -> {
                RejectAllScholarshipBySupervisorUseCase rejectAllScholarshipBySupervisorUseCase =
                        new RejectAllScholarshipBySupervisorUseCaseImpl();
                rejectAllScholarshipBySupervisorUseCase.rejectAll();
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
                AddUserUseCase createAccountByUserUseCase =
                        new AddUserUseCaseImpl();
                createAccountByUserUseCase.add(username, password, "Student");
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
                scholarshipReportByUniversityUseCase.report().forEach((K, V) -> System.out.println("Status :" + K + ", Count:" + V));
            }
            case "accept all" -> {
                AcceptAllScholarshipByUniversityUseCaseImpl acceptAllScholarshipByUniversityUseCase =
                        new AcceptAllScholarshipByUniversityUseCaseImpl();
                acceptAllScholarshipByUniversityUseCase.acceptAll();
            }
            case "reject all" -> {
                RejectAllScholarshipByUniversityUseCase rejectAllScholarshipByUniversityUseCase =
                        new RejectAllScholarshipByUniversityUseCaseImpl();
                rejectAllScholarshipByUniversityUseCase.rejectAll();
            }
            case "add account" -> {
                System.out.println("Role (Student,Supervisor,Manager,University) :");
                String role = scanner.nextLine();
                System.out.println("Username: ");
                String username = scanner.nextLine();
                System.out.println("Password: ");
                String password = scanner.nextLine();

                AddUserUseCase createAccountByUserUseCase =
                        new AddUserUseCaseImpl();
                createAccountByUserUseCase.add(username, password, role);
            }

            case "delete account" -> {
                System.out.println("Username: ");
                String username = scanner.nextLine();
                System.out.println("WARNING !!! \n Deleting a user eliminates all of his information including logs and scholarship!");
                System.out.println("Do you delete? (type yes or no)");
                DeleteUserUseCase deleteUserUseCase =
                        new DeleteUserUseCaseImpl();
                DeleteScholarshipUseCase deleteScholarshipUseCase =
                        new DeleteScholarshipUseCaseImpl();
                DeleteLogUseCase deleteLogUseCase =
                        new DeleteLogUseCaseImpl();

                FindScholarshipUseCase findScholarshipUseCase =
                        new FindScholarshipUseCaseImpl();
                List<Scholarship> scholarshipList = findScholarshipUseCase.findAllScholarshipByUsername(username);

                for (Scholarship scholarship : scholarshipList) {
                    deleteLogUseCase.deleteByScholarshipId(scholarship.getId());
                }

                for (Scholarship scholarship : scholarshipList) {
                    deleteScholarshipUseCase.deleteByScholarshipId(scholarship.getId());
                }

                deleteUserUseCase.delete(username);
                LogoutUseCase logoutUseCase = new LogoutUseCaseImpl();
                user = logoutUseCase.logout();
            }

            case "show account group by role" -> {
                System.out.println("Role (Student,Supervisor,Manager,University) :");
                String role = scanner.nextLine();
                FindUserUseCase findUserUseCase =
                        new FindUserUseCaseImpl();
                List<User> userList = findUserUseCase.findByRole(role);
                userList.forEach(System.out::println);
            }
        }

    }
}
