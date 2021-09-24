package Controller;

import java.util.Scanner;

public class Session {

    private Authorization authorizer;
    private Transaction transactor;

    public Session(){
        authorizer = new Authorization();
        transactor = new Transaction();
    }

    public boolean checkLoginNPassword(Scanner scanner){
        return authorizer.requestLoginNPassword(scanner);
    }

    public void checkBalance(){

    }
}
