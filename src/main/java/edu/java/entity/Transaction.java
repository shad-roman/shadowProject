package edu.java.entity;


import java.time.LocalDate;


public class Transaction  {
    String transaction;
    Integer cost;
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
