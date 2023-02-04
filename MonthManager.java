package SprintTwo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collections;
import java.util.*;

public class MonthManager {
    ValueMonth maxValue = new ValueMonth();
    HashMap<Integer, HashMap<Boolean, Double>> dataMonthSum = new HashMap<>();
    HashMap<Boolean, Double> dataSum;

    HashMap<Integer, ArrayList<MonthData>> monthRep = new HashMap<>();

    public void loadFiles(int monthNumber, String path) {
        ArrayList<MonthData> monthDat = new ArrayList<>();
        String contents = readFileContents(path);
        String[] lines = contents.split("\n");
        for (int i = 1; i < lines.length; i++) {
            String line = lines[i];
            String[] parts = line.split(",");// item_name,is_expense,quantity,sum_of_one
            String itemName = parts[0];
            boolean isExpense = Boolean.parseBoolean(parts[1]);
            int quantity = Integer.parseInt(parts[2]);
            int prise = Integer.parseInt(parts[3]);
            MonthData monthData = new MonthData(itemName, isExpense, quantity, prise);

            monthDat.add(monthData);

        }
        monthRep.put(monthNumber, monthDat);
        dataMonthSum.put(monthNumber, new HashMap<>());
    }

    public String readFileContents(String path) {
        try {
            return Files.readString(Path.of(path));
        } catch (IOException e) {
            System.out.println("Невозможно прочитать файл с месячным отчётом. Возможно файл не находится в нужной директории.");
            return null;
        }
    }

    public double findTopProduct() {
        double sumProfit = 0;
        System.out.println("Отчет о прибыли:");
        for (Integer month : monthRep.keySet()) {
            ArrayList<MonthData> valueMonth = monthRep.get(month);
            sumProfit = 0;
            for (MonthData monthData : valueMonth) {
                if (!monthData.isExpense) {
                    double profit = monthData.prise * monthData.quantity;
                    if (profit > maxValue.maxProfit) {
                        maxValue.maxProfit = profit;
                        maxValue.nameProfit = monthData.itemName;
                    }
                    sumProfit += profit;
                }
                dataSum = dataMonthSum.get(month);
            }
            System.out.println("В " + month + " месяце. Самый прибыльный товар  " + maxValue.nameProfit + " на сумму " + sumProfit);
        }
        return sumProfit;
    }

    public double findMaxExpenses() {
        System.out.println("Отчет о тратах:");

        double sumExpens = 0;
        for (Integer month : monthRep.keySet()) {
            ArrayList<MonthData> valueMonth = monthRep.get(month);
            sumExpens = 0;
            for (MonthData monthData : valueMonth) {
                if (monthData.isExpense) {
                    double expenses = monthData.prise * monthData.quantity;
                    if (expenses > maxValue.maxExpense) {
                        maxValue.maxExpense = expenses;
                        maxValue.nameExpense = monthData.itemName;
                    }
                    sumExpens += expenses;

                }
                dataSum = dataMonthSum.get(month);
            }
            System.out.println("В " + month + " месяце. Самая большая трата на товар: " + maxValue.nameExpense + " на сумму " + sumExpens);
        }
        return sumExpens;
    }

    public void mapForChecker() {
        double sumProfit = 0;
        double sumExpens = 0;
        for (Integer month : monthRep.keySet()) {
            ArrayList<MonthData> valueMonth = monthRep.get(month);
            sumExpens = 0;
            sumProfit = 0;
            for (MonthData monthData : valueMonth) {
                if (monthData.isExpense) {
                    double expenses = monthData.prise * monthData.quantity;
                    if (expenses > maxValue.maxExpense) {
                        maxValue.maxExpense = expenses;
                        maxValue.nameExpense = monthData.itemName;
                    }
                    sumExpens += expenses;


                } else {
                    double profit = monthData.prise * monthData.quantity;
                    if (profit > maxValue.maxProfit) {
                        maxValue.maxProfit = profit;
                        maxValue.nameProfit = monthData.itemName;
                    }
                    sumProfit += profit;
                }
                dataSum = dataMonthSum.get(month);
                dataSum.put(false, sumProfit);
                dataSum.put(true, sumExpens);
            }
        }
    }
}