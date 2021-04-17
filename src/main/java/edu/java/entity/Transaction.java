package edu.java.entity;


import com.opencsv.bean.CsvBindByPosition;
import com.opencsv.bean.CsvDate;

import java.time.LocalDate;


public class Transaction {
    @CsvBindByPosition(position = 0)
    String transaction;
    @CsvBindByPosition(position = 1)
    Integer cost;
    @CsvBindByPosition(position = 2)
    @CsvDate(value = "yyyy-MM-dd")
    LocalDate date;


    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public String getTransaction() {
        return transaction;
    }

    public void setTransaction(String transaction) {
        this.transaction = transaction;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transaction='" + transaction + '\'' +
                ", cost=" + cost +
                ", date=" + date +
                '}';
    }
}
