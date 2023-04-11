package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface StateDao {
    @Insert
    void insert(State state);
    @Update
    void update(State state);
    @Query("DELETE FROM current_state")
    void logoutState();
    @Query("SELECT * FROM current_state")
    LiveData<List<State>> getStates();

}
