package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface UsrDao {
    @Insert
    void insert(User user);

    @Delete
    void delete(User user);

    @Update
    void update(User user);

    @Query("SELECT * FROM user_table")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user_table where name is :name")
    LiveData<List<User>> getUserByName(String name);

    @Query("SELECT * FROM user_table where name is :name and password is :pass and id is :id")
    LiveData<List<User>> verifyUpdate(String name, String pass, int id);
}
