package edu.java.entity;

import java.time.LocalDate;
import java.util.Date;

public class Transaction {
    LocalDate date;
    Integer cost;
    String transaction;

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
                "date=" + date +
                ", cost=" + cost +
                ", transaction='" + transaction + '\'' +
                '}';
    }
}
