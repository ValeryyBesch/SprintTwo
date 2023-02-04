package SprintTwo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Checker {
    public YearManager yearManager;
    public MonthManager monthManager;


    public Checker(YearManager yearManager, MonthManager monthManager) {
        this.yearManager = yearManager;
        this.monthManager = monthManager;
    }

    public void check () {
        HashMap<Integer, HashMap<Boolean, Double>> yearReport = new HashMap<>();// мясяц тратаЛи сумма
        for(YearData yearData : yearManager.year) {
            if (!yearReport.containsKey(yearData.month)) {
                yearReport.put(yearData.month, new HashMap<>());
            }
            HashMap<Boolean, Double> expensToSum = yearReport.get(yearData.month);
            expensToSum.put(yearData.isExpense, yearData.amount);
        }
      HashMap<Integer, HashMap<Boolean, Double>> monthReport = monthManager.dataMonthSum; // мясяц тратаЛи сумма
        for (Integer month : monthReport.keySet()) {
            HashMap<Boolean, Double> profitOrExpenseMonth = monthReport.get(month);
            HashMap<Boolean, Double> profitOrExpenseYears = yearReport.get(month);
            for (Boolean expenses : profitOrExpenseMonth.keySet()) {
                double expensesByMonth = profitOrExpenseMonth.get(expenses);
                double expensesByYears = profitOrExpenseYears.get(expenses);
                if (expenses){
                if (expensesByMonth != expensesByYears){
                    System.out.println("В "+month +" месяце отчет о расходах расходиться");
                }
                }else {
                    if (expensesByMonth != expensesByYears){
                        System.out.println("В "+month +" месяце отчет о доходах расходиться");
                }
             }
           }
        }
    }
}
