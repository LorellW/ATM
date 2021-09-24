package Controller;

import Model.DataBaseChecker;

import java.util.Scanner;

public class Authorization {
    private DataBaseChecker checker;

    public Authorization(){
        checker = new DataBaseChecker();
    }

    protected boolean requestLoginNPassword(Scanner scanner){
        String login = scanner.nextLine();
        if (login.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")) {
            String password = scanner.nextLine();
            return checker.check(login + " " + password);
        } else {
            return false;
        }
    }
}
