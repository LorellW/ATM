import Controller.Session;

import java.util.Scanner;

public class ATM { // класс имитирующий работу "экрана" банкомата
    private Session session;
    private Scanner scanner = new Scanner(System.in);// Придумать что делать с потоком!!!!!

    public ATM() {
        session = new Session();
    }

    public void start() {
        welcomeView();
    }

    private void welcomeView() {
        System.out.println("""
                1. Авторизация\s
                0. Зактыть""");
        switch (checkAnswer(0, 1)) {
            case (0) -> System.out.println("close");
            case (1) -> authorizationView();
        }
    }

    private void authorizationView() {
        System.out.println("Введите номер карты и пароль");
        if (session.checkLoginNPassword(scanner)) {
            System.out.println("""
                    1. Продолжить\s
                    0. Зактыть""");
            switch (checkAnswer(0, 1)) {
                case (0) -> System.out.println("close");
                case (1) -> transactionView();
            }
        }else {
            System.out.println("""
                    Неверно введён номер карты и пароль
                    1. Повторить\s
                    0. Зактыть""");
            switch (checkAnswer(0, 1)) {
                case (0) -> System.out.println("close");
                case (1) -> authorizationView();
            }
        }
    }

    private void transactionView() {
        System.out.println("""
                Выберите интересующую операцию\s
                1. Баланс\s
                2. Пополнить счёт\s
                3. Снять со счёта\s
                0. Зактыть""");
        switch (checkAnswer(0, 1, 2, 3)) {
            case (0) -> System.out.println("close");
            case (1) -> balanceView();
            case (2) -> depositView();
            case (3) -> withdrawView();
        }
    }

    private void balanceView() {
        System.out.println("На счету столько-то денег");
        /*
        balance check
         */
        System.out.println("""
                1. Назад\s
                0. Зактыть""");
        switch (checkAnswer(0, 1)) {
            case (0) -> System.out.println("close");
            case (1) -> transactionView();
        }
    }

    private void depositView() {
        System.out.println("Введите сумму которую хотите внести");
        /*
            deposit to the account
         */
        System.out.println("""
                1. Назад\s
                0. Зактыть""");
        switch (checkAnswer(0, 1)) {
            case (0) -> System.out.println("close");
            case (1) -> transactionView();
        }
    }

    private void withdrawView() {
        System.out.println("Введите сумму которую хотите снять");
        /*
            withdraw from the account
         */
        System.out.println("""
                1. Назад\s
                0. Зактыть""");
        switch (checkAnswer(0, 1)) {
            case (0) -> System.out.println("close");
            case (1) -> transactionView();
        }
    }



    private int checkAnswer(int... answers) {

        int answer;
        do {
            try {
                answer = Integer.parseInt(scanner.nextLine());
                for (int i : answers) {
                    if (answer == i) {
                        return answer;
                    }
                }
                System.out.println("Выбранный пункт меню отсутствует, попробуйте ещё раз");
            } catch (NumberFormatException e) {
                System.out.println("Неверный формат, попробуйте ещё раз");
            }
        } while (true);
    }

}
