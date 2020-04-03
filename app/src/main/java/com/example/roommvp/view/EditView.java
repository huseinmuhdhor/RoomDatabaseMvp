package com.example.roommvp.view;

import com.example.roommvp.database.entity.Person;

public interface EditView {
    void showErrorMessage(int field);

    void clearPreErrors();

    void close();

    void populate(Person person);
}
