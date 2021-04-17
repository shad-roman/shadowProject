package edu.java;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.ColumnPositionMappingStrategy;
import au.com.bytecode.opencsv.bean.CsvToBean;
import edu.java.entity.Transaction;

import java.io.FileReader;
import java.io.IOException;
import java.util.List;
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
    }
}
