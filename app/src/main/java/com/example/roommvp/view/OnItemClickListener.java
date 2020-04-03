package com.example.roommvp.view;

import com.example.roommvp.database.entity.Person;

public interface OnItemClickListener {
    void clickItem(Person person);

    void clickLongItem(Person person);
}
