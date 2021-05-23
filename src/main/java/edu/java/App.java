package edu.java;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.java.entity.Shop;
import edu.java.entity.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class App {
    public static void main(String[] args) throws IOException, ParseException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("enter path to csv file");
        String csvFile = scanner.nextLine();
   //     String csvFile = "C:\\WORK_FOLDER\\java\\shadowProject\\src\\resources\\transaction.csv";

        List<Transaction> transactions = new CsvToBeanBuilder(new FileReader(csvFile))
                .withType(Transaction.class).build().parse();

  //      transactions.forEach(System.out::println);
        Map <LocalDate, List<Transaction>> dateMap = new HashMap<LocalDate, List<Transaction>>();
        buildDateMap(transactions, dateMap);

        Map <String, Integer> shopMap = new HashMap<String,Integer>();
        buildShopMap(transactions, shopMap);

        Map <String,Map <String, Integer>> cityShopMap= new HashMap<String, Map<String,Integer>>();
        buildCityShopMap (transactions, cityShopMap);

        Map <String, Integer> saleMap = new HashMap<String,Integer>();
        buildSaleMap (transactions, saleMap);



        Integer operation;
        do{
            printCommandDescr();
            operation = Integer.parseInt(scanner.nextLine());

            if (operation == 1) {
                printDateStats(scanner, dateMap);
            }

            if (operation == 2){
                printShopStats(scanner, shopMap, cityShopMap);
            }
            if (operation == 3){
                printSaleStats (scanner, saleMap);
            }
        } while (operation!=0);
    }

    private static void printSaleStats (Scanner scanner, Map<String, Integer> saleMap) {
        Integer productOperation;
        do {
            System.out.println("1 - print all data");
            System.out.println("2 - print data for special product");
            System.out.println("0 - back to main menu");
            productOperation = Integer.parseInt(scanner.nextLine());
            if (productOperation == 1) {
                printMapData(saleMap);
            }
            if (productOperation == 2) {
                System.out.println("enter product name");
                String strProduct = scanner.nextLine();
                if (saleMap.containsKey(strProduct)) {
                    System.out.printf("cost of sold %s - is %d%n", strProduct, saleMap.get(strProduct));
                } else {
                    System.out.println("no such product");
                }
            }

        } while (productOperation !=0);
    }

    private static void printShopStats(Scanner scanner, Map<String, Integer> shopMap, Map <String, Map<String, Integer>> cityShopMap) {
        Integer shopOperation;
        do {
            System.out.println("1 - print all data");
            System.out.println("2 - print data for special shop");
            System.out.println("0 - back to main menu");
            shopOperation = Integer.parseInt(scanner.nextLine());
            if (shopOperation == 1) {
                printMapData(shopMap);
            }
            if (shopOperation == 2) {
                Integer cityShopOperation;
                do {
                    System.out.println("1 - print data for shop in all country");
                    System.out.println("2 - print data for shop in special city");
                    System.out.println("0 - exit");
                    cityShopOperation = Integer.parseInt(scanner.nextLine());
                    if (cityShopOperation == 1) {
                        System.out.println("Enter shop name");
                        String strShop = scanner.nextLine();

                        if (shopMap.containsKey(strShop)) {
                            System.out.printf("in %s was spent %d$%n", strShop, shopMap.get(strShop));
                        } else {
                            System.out.println("no such shop");
                        }
                    }
                    if (cityShopOperation == 2) {
                        System.out.println("enter city");
                        String strCity = scanner.nextLine();

                        System.out.println("Enter shop name");
                        String strShop = scanner.nextLine();

                        if (cityShopMap.get(strCity).containsKey(strShop)) {
                            System.out.printf("in %s was spent %d$%n", strShop, cityShopMap.get(strCity).get(strShop));
                        } else {
                            System.out.println("no such shop");
                        }
                    }
                }while (cityShopOperation != 0);
            }
        }while (shopOperation != 0);
    }

    private static void printMapData (Map map){
        map.forEach((key, value) -> System.out.println(key + " : " + value));
    }

    private static void printDateStats(Scanner scanner, Map<LocalDate, List<Transaction>> dateMap) {
        Integer dateOperation;
        do {
            System.out.println("1 - print all data");
            System.out.println("2 - print data for special date");
            System.out.println("0 - back to main menu");
            dateOperation = Integer.parseInt(scanner.nextLine());
            if (dateOperation == 1) {
                printMapData(dateMap);
            }
            if (dateOperation == 2) {

                System.out.println("enter date");
                LocalDate date = LocalDate.parse(scanner.nextLine(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

                if (dateMap.containsKey(date)) {
                    List<Transaction> trans = dateMap.get(date);
                    Integer sum = 0;
                    for (Transaction transaction : trans) {
                        sum += transaction.getCost();
                    }
                    System.out.printf("on %s was spent %d$%n", date, sum);
                } else {
                    System.out.println("no transactions on this date");
                }
            }
        }while (dateOperation !=0);
    }


    private static void printCommandDescr() {
        System.out.println("choose option with transactions: ");
        System.out.println("1 - spent in a day");
        System.out.println("2 - spent in a shop");
        System.out.println("3 - spent for a special product");
        System.out.println("0 - exit");
    }

    private static void buildDateMap(List<Transaction> transactions, Map<LocalDate, List<Transaction>> dateMap) {
        for (Transaction transaction : transactions){

            LocalDate date = transaction.getDate();

            if (dateMap.containsKey(date)){
                dateMap.get(date).add(transaction);
            } else {
                List<Transaction> newTrans = new ArrayList<>();
                newTrans.add(transaction);
                dateMap.put(transaction.getDate(), newTrans);
            }
        }
    }

    private static void buildShopMap(List<Transaction> transactions, Map<String, Integer> shopMap) {
        for (Transaction transaction : transactions){
            String shopName = transaction.getShop().getName();

            if (shopMap.containsKey(shopName)){
                Integer sum = shopMap.get(shopName) + transaction.getCost();
                shopMap.put(shopName,sum);
            } else {
                shopMap.put(shopName, transaction.getCost());
            }
        }
    }

    private static void buildSaleMap (List<Transaction> transactions, Map<String, Integer> saleMap){
        for (Transaction transaction : transactions){
            String trans = transaction.getTransaction();
            if (saleMap.containsKey(trans)){
                Integer sum = saleMap.get(trans) + transaction.getCost();
                saleMap.put(trans, sum);
            } else {
                saleMap.put(trans, transaction.getCost());
            }
        }
    }
    private static  void buildCityShopMap (List<Transaction> transactions,  Map<String, Map<String, Integer>> cityShopMap){
        for (Transaction transaction : transactions){
            String city = transaction.getShop().getCity();
            String shopName = transaction.getShop().getName();

            if (cityShopMap.containsKey(city)) {
                Map<String, Integer> shopMap = cityShopMap.get(city);
                    if (shopMap.containsKey(shopName)) {
                        Integer sum = shopMap.get(shopName) + transaction.getCost();
                        shopMap.put(shopName, sum);
                    } else {
                        shopMap.put(shopName, transaction.getCost());
                    }
                cityShopMap.put(city, shopMap);
            } else {
                Map<String, Integer> shopMap = new HashMap<String,Integer>();
                shopMap.put(shopName, transaction.getCost());
                cityShopMap.put (city, shopMap);
            }
        }
    }
}
