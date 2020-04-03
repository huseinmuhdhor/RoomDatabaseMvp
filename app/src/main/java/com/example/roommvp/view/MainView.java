package com.example.roommvp.view;

import com.example.roommvp.database.entity.Person;

import java.util.List;

public interface MainView {
    void showAddPerson();

    void setPersons(List<Person> persons);

    void showEditScreen(long id);

    void showDeleteConfirmDialog(Person person);

    void showEmptyMessage();
}
