package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "transaction_table", foreignKeys = {@ForeignKey(entity = User.class, parentColumns = "id", childColumns = "UserKey"
, onDelete = ForeignKey.CASCADE)})
public class Transaction {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String source;
    private double amount;
    private int UserKey;
    private String date;

    public Transaction(String source, double amount, int userKey, String date) {
        this.source = source;
        this.amount = amount;
        this.date = date;
        UserKey = userKey;
    }

    public Transaction() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getUserKey() {
        return UserKey;
    }

    public void setUserKey(int userKey) {
        UserKey = userKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
