package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "current_state")
public class State{
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int currentUserID;
    private String password;
    private String name;


    public State(int currentUserID, String password, String name) {
        this.currentUserID = currentUserID;
        this.password = password;
        this.name = name;
    }

    public State() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public int getCurrentUserID() {
        return currentUserID;
    }

    public void setCurrentUserID(int currentUserID) {
        this.currentUserID = currentUserID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
