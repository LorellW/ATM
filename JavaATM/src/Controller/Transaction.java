package Controller;

import Model.DataBaseChecker;

public class Transaction {
    private DataBaseChecker checker;

    public Transaction(DataBaseChecker checker) {
        this.checker = checker;
    }

    protected int requestBalance() {
        return checker.getBalance();
    }

    protected boolean requestDeposit(int deposit) {
        if (deposit < 0) {
            System.out.println("Вы не можете пополнить счёт на отрицательную величину");
            return false;
        } else if (deposit >= 1000000) {
            System.out.println("Пополнение на сумму превышающюю 1 000 000 недопустима");
            return false;
        } else {
            int oldBalance = checker.getBalance();
            int newBalance = oldBalance + deposit;
            checker.transactionRequest(newBalance);
            return true;
        }
    }

    protected boolean requestWithdraw(int withdrawal, int limit) {
        int oldBalance = checker.getBalance();
        if (withdrawal < 0) {
            System.out.println("Вы не можете снять со счёта отрицательную величину");
            return false;
        } else if (oldBalance < withdrawal) {
            System.out.println("Недостаточно средств");
            return false;
        } else if (withdrawal > limit) {
            System.out.println("Превышен лимит банкомата");
            return false;
        } else {
            int newBalance = oldBalance - withdrawal;
            checker.transactionRequest(newBalance);
            return true;
        }
    }
}
