package com.example.roommvp.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = "people")
public class Person {

    @PrimaryKey(autoGenerate = true)
    public long id;
    public String name;
    public String address;
    public String phone;
    public String email;

    public Person() {
        this.name = "";
        this.address = "";
        this.phone = "";
        this.email = "";
    }

    public Person(String name, String address, String phone, String email) {
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
}
