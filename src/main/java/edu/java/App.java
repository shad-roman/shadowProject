package edu.java;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.java.entity.Shop;
import edu.java.entity.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class App {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter path to csv file");
        String csvFile = scanner.nextLine();
   //     String csvFile = "C:\\WORK_FOLDER\\java\\shadowProject\\src\\resources\\transaction.csv";
        scanner.close();
        List<Transaction> transactions = new CsvToBeanBuilder(new FileReader(csvFile))
                .withType(Transaction.class).build().parse();

  //      transactions.forEach(System.out::println);
        Map <LocalDate, List<Transaction>> dateMap = new HashMap<LocalDate, List<Transaction>>();
        for (int i =0; i < transactions.size(); i++){
            Transaction element = transactions.get(i);
            LocalDate date = element.getDate();
            if (dateMap.containsKey(date)){
                List<Transaction> trans = dateMap.get(date);
                trans.add(element);
                dateMap.replace(date,trans);
            } else {
                List<Transaction> newTrans = new ArrayList<>();
                newTrans.add(element);
                dateMap.put(element.getDate(), newTrans);
            }

        }
        dateMap.forEach((key, value) -> System.out.println(key + " : " + value));
        Map <String, Integer> shopMap = new HashMap<String,Integer>();
        for (int i=0; i<shopMap.size(); i++){
            Transaction element = transactions.get(i);
            Shop shop = element.getShop();
            String shopName = shop.getName();
            Integer cost = shopMap.get(shopName);
            if (shopMap.containsKey(shopName)){
                Integer sum = cost + element.getCost();
                shopMap.replace(shopName,sum);
            } else {
                shopMap.put(shopName, cost);
            }

        }
        Integer operation;
        do{
            Scanner in = new Scanner(System.in);
            System.out.println("choose option with transactions: ");
            System.out.println("1 - spent in a day");
            System.out.println("2 - spent in a shop");
            operation = in.nextInt();
            if (operation == 1) {
                Scanner sc = new Scanner(System.in);
                System.out.println("enter date");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDate date = LocalDate.parse(sc.nextLine(), formatter);
                if (dateMap.containsKey(date)) {
                    List<Transaction> trans = dateMap.get(date);
                    Integer sum = 0;
                    for (int i = 0; i < trans.size(); i++) {
                        Transaction transaction = trans.get(i);
                        Integer cost = transaction.getCost();
                        sum = cost + sum;
                    }
                    System.out.println("on " + date + " was spent " + sum + "$");
                } else {
                    System.out.println("no transactions on this date");
                }
                sc.close();
            }
            if (operation == 2){
                Scanner scanner1 = new Scanner(System.in);
                System.out.println("Enter shop name");
                String strShop = scanner1.nextLine();
                if (shopMap.containsKey(strShop)){
                    System.out.println("in " + strShop + "was spent " + shopMap.get(strShop) + "$");
                } else {
                    System.out.println("no such shop");
                }
                scanner1.close();
            }
            in.close();
        } while (operation!=0);
    }
}
