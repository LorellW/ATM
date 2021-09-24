package Controller;

import java.util.Scanner;

public class Session {

    private Authorization authorizer;
    private Transaction transactor;

    public Session(){
        authorizer = new Authorization();
        transactor = new Transaction(authorizer.transferChecker());
    }

    public boolean checkLoginNPassword(Scanner scanner){
        return authorizer.requestLoginNPassword(scanner);
    }

    public int checkBalance(){
       return transactor.requestBalance();
    }

    public void putMoney(int deposit){
        transactor.requestDeposit(deposit);
    }

    public void takeMoney(){
        transactor.requestWithdraw();
    }
}
