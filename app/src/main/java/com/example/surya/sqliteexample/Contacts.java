package com.example.surya.sqliteexample;

/**
 * Created by surya on 11/11/16.
 */
public class Contacts {

    private int id;
    private String Name;
    private String phoneNumber;

    public Contacts(int id, String name, String phoneNumber) {
        this.id = id;
        Name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contacts(String name, String phoneNumber) {
        Name = name;
        this.phoneNumber = phoneNumber;
    }

    public Contacts() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public String toString() {
        return "Contacts{" +
                "id=" + id +
                ", Name='" + Name + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
