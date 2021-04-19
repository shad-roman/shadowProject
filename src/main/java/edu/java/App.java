package edu.java;

<<<<<<< HEAD
import com.opencsv.bean.CsvToBeanBuilder;
=======
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
>>>>>>> 1abd7810a08ba446b62701fb7dccd3c69d0d56cf
import edu.java.entity.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
<<<<<<< HEAD


public class App {
    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\WORK_FOLDER\\java\\shadowProject\\src\\resources\\transaction.csv";

        List<Transaction> transactions = new CsvToBeanBuilder(new FileReader(csvFile))
                .withType(Transaction.class).build().parse();


        transactions.forEach(System.out::println);

        System.out.println(transactions.size());
        System.out.println(transactions.get(1));

=======
public class App 
{
    public static void main( String[] args ) throws IOException {
        CsvToBean csv = new CsvToBean();
        String csvFile = "C:\\WORK_FOLDER\\java\\shadowProject\\src\\resources\\transaction.csv";
        CSVReader reader = new CSVReader(new FileReader(csvFile));
        List list = csv.parse(setColumnMapping(), reader);
        for (Object object : list) {
            Transaction transaction = (Transaction) object;
            System.out.println(transaction);
        }
    }
    public static ColumnPositionMappingStrategy setColumnMapping (){
        ColumnPositionMappingStrategy strategy = new ColumnPositionMappingStrategy();
        strategy.setType(Transaction.class);
        String[] columns = new String[] {"transaction", "cost", "date"};
        strategy.setColumnMapping(columns);
        return strategy;
>>>>>>> 1abd7810a08ba446b62701fb7dccd3c69d0d56cf
    }
}
