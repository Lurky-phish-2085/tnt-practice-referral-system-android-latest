package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "reference_table", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "UserKey"
        , onDelete = ForeignKey.CASCADE)})
public class ReferenceKey {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String code;
    private int UserKey;

    public ReferenceKey(String code, int userKey) {
        this.code = code;
        UserKey = userKey;
    }

    public ReferenceKey() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getUserKey() {
        return UserKey;
    }

    public void setUserKey(int userKey) {
        UserKey = userKey;
    }
}
