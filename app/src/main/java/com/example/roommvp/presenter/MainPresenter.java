package com.example.roommvp.presenter;

import com.example.roommvp.database.entity.Person;

public interface MainPresenter {
    void addNewPerson();

    void result(int requestCode, int resultCode);

    void populatePeople();

    void openEditScreen(Person person);

    void openConfirmDeleteDialog(Person person);

    void delete(long personId);
}
