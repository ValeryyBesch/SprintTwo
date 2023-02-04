package SprintTwo;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MonthManager monthManager = new MonthManager();
        YearManager yaerManager =new YearManager();
      Checker checker = new Checker(yaerManager, monthManager);
        printMenu();
        while (true){
          int menu = scanner.nextInt();
          if (menu == 1){
              System.out.println("Считываю все месячные отчеты....");
              for (int i = 1; i < 4; i++){
                  monthManager.loadFiles(i,"Data/m.20210"+ i +".csv");
              }
              System.out.println("Успех!");
          } else if (menu == 2) {
              System.out.println("Считываю годовой отчет....");
              yaerManager.readYear("Data/y.2021.csv");
              System.out.println("Успех!");
          } else if (menu == 3) {
              if (!monthManager.monthRep.isEmpty() && !yaerManager.year.isEmpty()){
              monthManager.mapForChecker();
              checker.check();
              }else {
                  System.out.println("Сначала нужно счиать оба отчета!");
              }
          } else if (menu == 4) {
              if (!monthManager.monthRep.isEmpty()){
             monthManager.findTopProduct();
             monthManager.findMaxExpenses();
              }else System.out.println("Нужно сначала считать месячные отчеты");
          } else if (menu == 5) {
              if (!yaerManager.year.isEmpty()) {
                  yaerManager.profitInMonth();
                  yaerManager.getAvgValue();
              }else System.out.println("Нужно сначала считать годовой отчет");
          } else if (menu == 6) {
              break;
          }else {
              System.out.println("Что-то не то (");
          }
        }
    }

    static void printMenu(){
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Выход");
    }
}
