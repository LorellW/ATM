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
        String login = scanner.nextLine();
        String password = scanner.nextLine();
        return authorizer.requestLoginNPassword(login,password);
    }

    public int checkBalance(){
       return transactor.requestBalance();
    }

    public boolean putMoney(Scanner scanner){
        try {
            int deposit = Integer.parseInt(scanner.nextLine());
            return transactor.requestDeposit(deposit);
        }catch (NumberFormatException e){
            System.out.println("Неверный формат, попробуйте ещё раз");
            return false;
        }
    }

    public boolean takeMoney(Scanner scanner){
        try {
            int withdrawal = Integer.parseInt(scanner.nextLine());
            return transactor.requestWithdraw(withdrawal);
        }catch (NumberFormatException e){
            System.out.println("Неверный формат, попробуйте ещё раз");
            return false;
        }
    }

    public void close(){
        authorizer.closeNWrite();
    }
}
