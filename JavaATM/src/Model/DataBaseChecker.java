package Model;

import java.io.*;

public class DataBaseChecker {
    private final String PATH = "JavaATM/database/database.txt";
    private int balance;
    private String account;

    public boolean check(String request) {//метод для поиска в "БД"
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            while (reader.ready()) {
                String checked = reader.readLine();
                if (isContains(request, checked)) {
                    System.out.println(checked);
                    account = request;
                    balance = getNSet(checked.substring(25));
                    System.out.println(balance);
                    return true;
                }
                if (!reader.ready()) {
                    return false;
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка связи с базой данных");
        }
        return false;
    }

    public void update(String request) {

    }

    private void replaceSelected(String replaceWith, String replaceTo){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(PATH));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            reader.close();

            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(replaceWith, replaceTo);

            FileOutputStream fileOut = new FileOutputStream(PATH);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        }catch (IOException e){
            System.out.println("Ошибка связи с базой данных");
        }
    }

    private boolean isContains(String request, String checked) {
        return checked.substring(0, 24).equals(request);
    }

    private int getNSet(String substr) {
        return Integer.parseInt(substr.substring(0, substr.indexOf(" ")));
    }

    public int getBalance() {
        return balance;
    }
}
