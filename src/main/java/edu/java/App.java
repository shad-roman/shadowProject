package edu.java;

import com.opencsv.bean.CsvToBeanBuilder;
import edu.java.entity.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class App {
    public static void main(String[] args) throws IOException {
        String csvFile = "C:\\WORK_FOLDER\\java\\shadowProject\\src\\resources\\transaction.csv";

        List<Transaction> transactions = new CsvToBeanBuilder(new FileReader(csvFile))
                .withType(Transaction.class).build().parse();


        transactions.forEach(System.out::println);

        System.out.println(transactions.size());
        System.out.println(transactions.get(1));

    }
}
