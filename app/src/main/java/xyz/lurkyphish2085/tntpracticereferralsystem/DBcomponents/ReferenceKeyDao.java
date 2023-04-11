package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ReferenceKeyDao {
    @Insert
    void insert(ReferenceKey referenceKey);

    @Delete
    void delete(ReferenceKey referenceKey);

    @Update
    void update(ReferenceKey referenceKey);

    @Query("SELECT * FROM reference_table")
    LiveData<List<ReferenceKey>> getAllReferences();

    @Query("SELECT * FROM reference_table where UserKey is :UserKey")
    LiveData<List<ReferenceKey>> getReferenceByUserKey(int UserKey);

    @Query("SELECT * FROM reference_table where code is :code")
    LiveData<List<ReferenceKey>> getReferenceByCode(String code);
}
