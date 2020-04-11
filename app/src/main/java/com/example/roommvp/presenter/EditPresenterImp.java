package com.example.roommvp.presenter;

import com.example.roommvp.database.dao.PersonDao;
import com.example.roommvp.database.entity.Person;
import com.example.roommvp.utils.Constants;
import com.example.roommvp.utils.Util;
import com.example.roommvp.view.EditView;

public class EditPresenterImp implements EditPresenter {

    private final EditView view;
    private final PersonDao personDao;

    public EditPresenterImp(EditView view, PersonDao personDao) {
        this.view = view;
        this.personDao = personDao;
    }

    @Override
    public void save(Person person) {
        this.personDao.insertPerson(person);
        view.close();
    }

    @Override
    public boolean validate(Person person) {
        view.clearPreErrors();
        if (person.name.isEmpty() || !Util.isValidName(person.name)) {
            view.showErrorMessage(Constants.FIELD_NAME);
            return false;
        }
        if (person.address.isEmpty()) {
            view.showErrorMessage(Constants.FIELD_ADDRESS);
            return false;
        }
        if (person.phone.isEmpty() || !Util.isValidPhone(person.phone)) {
            view.showErrorMessage(Constants.FIELD_PHONE);
            return false;
        }
        if (person.email.isEmpty() || !Util.isValidEmail(person.email)) {
            view.showErrorMessage(Constants.FIELD_EMAIL);
            return false;
        }

        return true;
    }

    @Override
    public void getPersonAndPopulate(long id) {
        Person person = personDao.findPerson(id);
        if (person != null) {
            view.populate(person);
        }
    }

    @Override
    public void update(Person person) {
        int ids = this.personDao.updatePerson(person);
        view.close();
    }
}
