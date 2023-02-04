package SprintTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class YearManager {
    HashMap<Integer, Double> valueYears = new HashMap<>();
    public ArrayList<YearData> year = new ArrayList<>();

public void readYear (String path){
    String content = readFileContents(path);
    String[] lines = content.split("\n");
    for (int i = 1; i < lines.length; i++) {
        String line = lines[i];
        String[] parts = line.split( ","); // month,amount,is_expense
        int month = Integer.parseInt(parts[0]);
        int amount= Integer.parseInt(parts[1]);
        boolean isExpense = Boolean.parseBoolean(parts[2]);
        YearData yearData = new YearData(month, amount, isExpense);
        year.add(yearData);
    }
}
    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path)) ;
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }
    public void profitInMonth(){
        for (YearData valueYear : year) {
            if(!valueYear.isExpense) {
                valueYears.put(valueYear.month, valueYears.getOrDefault(valueYear.month, 0.0) + valueYear.amount);
            } else {
                valueYears.put(valueYear.month, valueYears.getOrDefault(valueYear.month, 0.0) + (-valueYear.amount));
            }
        }
        for (java.util.Map.Entry<Integer, Double> entry : valueYears.entrySet()) {
            System.out.println("В " + entry.getKey() + " месяце. Прибыль равна: " + entry.getValue());
    }
}
public void getAvgValue(){
    double avgProfit;
    double avgExpense;
    double sumProfit = 0.0;
    double sumExpense = 0.0;
    ArrayList<Integer> month = new ArrayList<>();
    for(YearData yearData : year) {
        month.add(yearData.month);
        if (yearData.isExpense) {
            sumExpense += yearData.amount;
        } else {
            sumProfit += yearData.amount;
        }
    }
    avgProfit = sumProfit / (month.size() /2);
    avgExpense = sumExpense / (month.size() /2);
    System.out.println("Средний доход равен: "+ avgProfit);
    System.out.println("Средний расход равен: "+ avgExpense);
}
}
