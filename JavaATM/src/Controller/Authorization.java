package Controller;

import Model.DataBaseChecker;


public class Authorization {
    private DataBaseChecker checker;

    public Authorization(){
        checker = new DataBaseChecker();
    }

    protected boolean requestLoginNPassword(String login, String password){
        if (login.matches("^\\d{4}-\\d{4}-\\d{4}-\\d{4}$")) {
            return checker.check(login + " " + password);
        } else {
            return false;
        }
    }
    protected DataBaseChecker transferChecker(){
        return checker;
    }

    protected void closeNWrite(){
        checker.update();
    }
}
