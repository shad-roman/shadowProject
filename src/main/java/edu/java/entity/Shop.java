package edu.java.entity;

import com.opencsv.bean.CsvBindByPosition;

public class Shop {
    @CsvBindByPosition(position = 3)
    String name;
    @CsvBindByPosition(position = 4)
    String city;
    @CsvBindByPosition(position = 5)
    String street;
    @CsvBindByPosition(position = 6)
    Integer houseNumber;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public Integer getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(Integer houseNumber) {
        this.houseNumber = houseNumber;
    }

    @Override
    public String toString() {
        return "Shop{" +
                "name='" + name + '\'' +
                ", city='" + city + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber=" + houseNumber +
                '}';
    }
}
