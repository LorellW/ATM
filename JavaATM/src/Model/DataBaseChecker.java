package Model;

import java.io.*;

public class DataBaseChecker {
    private final String PATH = "JavaATM/database/database.txt";
    private final String TEMP = "JavaATM/database/temp.txt";
    /*
    файл temp и методы манипуляций с ним созданы как своего рода бэк-ап на случай
    непредвиденного краша или экстренного закрытия приложения, т.е.
    не через предусмотренную опцию "закрыть"
     */

    public DataBaseChecker() {
        update();
    }

    public boolean check(String request) {
        try (BufferedReader reader = new BufferedReader(new FileReader(PATH))) {
            while (reader.ready()) {
                String checked = reader.readLine();
                if (isContains(request, checked)) {
                    createTemp(checked);
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
        try {
            BufferedReader readerTemp = new BufferedReader(new FileReader(TEMP));
            replaceTo = readerTemp.readLine();
            readerTemp.close();
            if (replaceTo != null) {
                BufferedReader readerPath = new BufferedReader(new FileReader(PATH));
                while (readerPath.ready()) {
                    replaceWith = readerPath.readLine();
                    if (isContains(replaceWith.substring(0, 24), replaceTo.substring(0, 24))) {
                        replaceSelected(replaceWith, replaceTo, PATH);
                        break;
                    }
                }
                readerPath.close();
            }
            FileWriter clear = new FileWriter(TEMP);
            clear.close();
        } catch (IOException e) {
            System.out.println("Ошибка связи с базой данных");
        }
    }

    private void createTemp(String updated) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TEMP))) {
            writer.write(updated);
        } catch (IOException e) {
            System.out.println("Ошибка связи с базой данных updTemp");
        }
    }

    private void replaceSelected(String replaceWith, String replaceTo, String filePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            StringBuilder inputBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                inputBuilder.append(line);
                inputBuilder.append('\n');
            }
            reader.close();

            String inputStr = inputBuilder.toString();
            inputStr = inputStr.replaceFirst(replaceWith, replaceTo);

            FileOutputStream fileOut = new FileOutputStream(filePath);
            fileOut.write(inputStr.getBytes());
            fileOut.close();
        } catch (IOException e) {
            System.out.println("Ошибка связи с базой данных replaceSelected");
        }
    }

    private boolean isContains(String request, String checked) {
        return checked.substring(0, 24).equals(request);
    }

    public int getBalance() {
        int balance = 0;
        String subStr = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(TEMP))) {
            while (reader.ready()) {
                subStr = reader.readLine();
            }
            String temp = subStr.substring(25);
            balance = Integer.parseInt(temp.substring(0, temp.indexOf(" ")));
        } catch (IOException e) {
            System.out.println("Ошибка связи с базой данных getBalance");
        }
        return balance;
    }

    public void transactionRequest(int change) {
        replaceSelected(Integer.toString(getBalance()), Integer.toString(change), TEMP);
    }
}
