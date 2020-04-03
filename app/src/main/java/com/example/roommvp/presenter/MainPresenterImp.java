package com.example.roommvp.presenter;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;

import com.example.roommvp.database.dao.PersonDao;
import com.example.roommvp.database.entity.Person;
import com.example.roommvp.view.MainView;

import java.util.List;

public class MainPresenterImp implements MainPresenter {

    private final MainView view;
    private final PersonDao personDao;

    public MainPresenterImp(MainView view, PersonDao personDao) {
        this.view = view;
        this.personDao = personDao;
    }

    @Override
    public void addNewPerson() {
        view.showAddPerson();
    }

    @Override
    public void result(int requestCode, int resultCode) {

    }

    @Override
    public void populatePeople() {
        personDao.findAllPersons().observeForever(new Observer<List<Person>>() {
            @Override
            public void onChanged(@Nullable List<Person> persons) {
                view.setPersons(persons);
                if (persons == null || persons.size() < 1) {
                    view.showEmptyMessage();
                }
            }
        });
    }

    @Override
    public void openEditScreen(Person person) {
        view.showEditScreen(person.id);
    }

    @Override
    public void openConfirmDeleteDialog(Person person) {
        view.showDeleteConfirmDialog(person);
    }

    @Override
    public void delete(long personId) {
        Person person = personDao.findPerson(personId);
        personDao.deletePerson(person);
    }
}
