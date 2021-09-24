package Model;

import java.io.*;

public class DataBaseChecker {
    private final String PATH = "JavaATM/database/database.txt";
    private final String TEMP = "JavaATM/database/temp.txt"; //временный файл введённый на случай защиты от экстренных крашей приложения

    public DataBaseChecker(){

    }

    public boolean check(String request) {//метод для поиска в "БД"
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            while (reader.ready()) {
                String checked = reader.readLine();
                if (isContains(request, checked)) {
                    System.out.println(checked);
                    updTemp(checked);
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

    public void update() {
        String replaceWith;
        String replaceTo;

        try(BufferedReader reader = new BufferedReader(new FileReader(TEMP))){
            while (reader.ready()){
                replaceTo = reader.readLine();
            }
        }catch (IOException e){
            System.out.println("Ошибка связи с базой данных updateReplaceTo");
        }

    }

    private void updTemp(String updated){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP))){
            writer.write(updated);
        }catch (IOException e){
            System.out.println("Ошибка связи с базой данных updTemp");
        }
    }

    private void replaceSelected(String replaceWith, String replaceTo, String filePath){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuffer inputBuffer = new StringBuffer();
            String line;

            while ((line = reader.readLine()) != null) {
                inputBuffer.append(line);
                inputBuffer.append('\n');
            }
            reader.close();

            String inputStr = inputBuffer.toString();
            inputStr = inputStr.replace(replaceWith, replaceTo);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        }catch (IOException e){
            System.out.println("Ошибка связи с базой данных replaceSelected");
        }
    }

    private boolean isContains(String request, String checked) {
        return checked.substring(0, 24).equals(request);
    }

    public int getBalance() {
        int balance = 0;
        String subStr = null;
        try(BufferedReader reader = new BufferedReader(new FileReader(TEMP))){
            while (reader.ready()){
                subStr=reader.readLine();
            }
            String temp = subStr.substring(25);
            balance = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));
        }catch (IOException e){
            System.out.println("Ошибка связи с базой данных getBalance");
        }
        return balance;
    }

    public void transactionRequest(int change){
        replaceSelected(Integer.toString(getBalance()),Integer.toString(change),TEMP);
    }
}
