package Model;

import java.io.*;

public class DataBaseChecker {
    private final String PATH = "JavaATM/database/database.txt";

    public boolean check(String request){//метод для поиска в "БД"
        try(BufferedReader reader = new BufferedReader(new FileReader(PATH))){
            while (reader.ready()){
                String checked = reader.readLine();
                if (isContains(request,checked)){
                    System.out.println(checked);
                    return true;
                }
                if (!reader.ready()){
                    return false;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean isContains(String request, String checked){
        return checked.substring(0, 24).equals(request);
    }
}
