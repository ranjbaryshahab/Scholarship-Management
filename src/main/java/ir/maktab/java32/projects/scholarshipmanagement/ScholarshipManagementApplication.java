package ir.maktab.java32.projects.scholarshipmanagement;

import ir.maktab.java32.projects.scholarshipmanagement.core.utilities.Menu;

@SuppressWarnings("Duplicates")
public class ScholarshipManagementApplication {
    public static void main(String[] args) {

        while (!Menu.command.equals("exit")) {
            System.out.println("Please choose of menu and type it: ");
            if (Menu.user == null) {
                Menu.defaultMenu();
            } else if (Menu.user.getRole().equals("Supervisor")) {
                Menu.supervisorMenu();
            } else if (Menu.user.getRole().equals("Student")) {
                Menu.studentMenu();
            } else if (Menu.user.getRole().equals("Manager")) {
                Menu.managerMenu();
            } else if (Menu.user.getRole().equals("University")) {
                Menu.universityMenu();
            }
        }
    }
}
