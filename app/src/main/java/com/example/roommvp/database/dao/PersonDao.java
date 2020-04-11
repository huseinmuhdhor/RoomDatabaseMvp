package com.example.roommvp.database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.roommvp.database.entity.Person;

import java.util.List;

import static androidx.room.OnConflictStrategy.IGNORE;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM people ORDER BY name ASC")
    LiveData<List<Person>> findAllPersons();

    @Query("SELECT * FROM people WHERE id=:id")
    Person findPerson(long id);

    @Insert(onConflict = IGNORE)
    void insertPerson(Person person);

    @Update
    int updatePerson(Person person);

    @Delete
    void deletePerson(Person person);
}
