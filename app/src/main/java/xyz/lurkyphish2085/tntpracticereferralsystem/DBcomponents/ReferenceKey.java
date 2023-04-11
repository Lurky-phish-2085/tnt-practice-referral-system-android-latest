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

    private int uplineId;

    public ReferenceKey(String code, int userKey, int uplineId) {
        this.code = code;
        UserKey = userKey;
        this.uplineId = uplineId;
    }

    public ReferenceKey() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUplineId() {
        return this.uplineId;
    }

    public void setUplineId(int uplineId) {
        this.uplineId = uplineId;
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
