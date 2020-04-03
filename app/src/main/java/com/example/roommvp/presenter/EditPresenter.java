package com.example.roommvp.presenter;

import com.example.roommvp.database.entity.Person;

public interface EditPresenter {

    void save(Person person);

    boolean validate(Person person);

    void getPersonAndPopulate(long id);

    void update(Person person);
}
