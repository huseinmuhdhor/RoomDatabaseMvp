package com.example.roommvp.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.roommvp.R;
import com.example.roommvp.database.AppDatabase;
import com.example.roommvp.database.entity.Person;
import com.example.roommvp.presenter.EditPresenterImp;
import com.example.roommvp.utils.Constants;
import com.example.roommvp.view.EditView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class EditActivity extends AppCompatActivity implements EditView {

    private EditPresenterImp presenterImp;

    private EditText mEditName;
    private EditText mEditAddress;
    private EditText mEditEmail;
    private EditText mEditPhone;

    private TextInputLayout mTextInputLayoutName;
    private TextInputLayout mTextInputLayoutAddress;
    private TextInputLayout mTextInputLayoutEmail;
    private TextInputLayout mTextInputLayoutPhone;

    private FloatingActionButton mFab;

    private Person person;
    private boolean editMode = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        person = new Person();
        checkMode();

        AppDatabase db = AppDatabase.getDatabase(getApplication());
        presenterImp = new EditPresenterImp(this, db.personModel());

        initViews();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (editMode) {
            presenterImp.getPersonAndPopulate(person.id);
        }
    }

    private void checkMode() {
        if (getIntent().getExtras() != null) {
            person.id = getIntent().getLongExtra(Constants.PERSON_ID, 0);
            editMode = true;
        }
    }

    private void initViews() {
        mEditName = findViewById(R.id.et_name);
        mEditAddress = findViewById(R.id.et_address);
        mEditEmail = findViewById(R.id.et_email);
        mEditPhone = findViewById(R.id.et_phone);

        mTextInputLayoutName = findViewById(R.id.ti_name);
        mTextInputLayoutAddress = findViewById(R.id.ti_address);
        mTextInputLayoutEmail = findViewById(R.id.ti_email);
        mTextInputLayoutPhone = findViewById(R.id.ti_phone);

        mFab = findViewById(R.id.fab);
        mFab.setImageResource(editMode ? R.drawable.ic_refresh : R.drawable.ic_done);
        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                person.name = mEditName.getText().toString();
                person.address = mEditAddress.getText().toString();
                person.email = mEditEmail.getText().toString();
                person.phone = mEditPhone.getText().toString();

                boolean valid = presenterImp.validate(person);

                if (!valid) return;

                if (editMode) {
                    presenterImp.update(person);
                } else {
                    presenterImp.save(person);
                }
            }
        });
    }

    @Override
    public void showErrorMessage(int field) {
        if (field == Constants.FIELD_NAME) {
            mTextInputLayoutName.setError(getString(R.string.invalid_name));
        } else if (field == Constants.FIELD_EMAIL) {
            mTextInputLayoutEmail.setError(getString(R.string.invalid_email));
        } else if (field == Constants.FIELD_PHONE) {
            mTextInputLayoutPhone.setError(getString(R.string.invalid_phone));
        } else if (field == Constants.FIELD_ADDRESS) {
            mTextInputLayoutAddress.setError(getString(R.string.invalid_address));
        }
    }

    @Override
    public void clearPreErrors() {
        mTextInputLayoutName.setErrorEnabled(false);
        mTextInputLayoutEmail.setErrorEnabled(false);
        mTextInputLayoutPhone.setErrorEnabled(false);
        mTextInputLayoutAddress.setErrorEnabled(false);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void populate(Person person) {
        this.person = person;
        mEditName.setText(person.name);
        mEditAddress.setText(person.address);
        mEditEmail.setText(person.email);
        mEditPhone.setText(person.phone);
    }
}
