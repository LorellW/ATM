package Controller;

import Model.DataBaseChecker;

public class Transaction {
    private DataBaseChecker checker;
    //private int newBalance;

    public Transaction(DataBaseChecker checker){
        this.checker = checker;
        //this.newBalance = checker.getBalance();
    }

    protected int requestBalance(){
        return checker.getBalance();
    }

    protected void requestDeposit(int deposit){

    }

    protected void requestWithdraw(){

    }
}
