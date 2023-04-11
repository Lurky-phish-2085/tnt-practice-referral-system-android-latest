package xyz.lurkyphish2085.tntpracticereferralsystem.DBcomponents;

import androidx.lifecycle.LiveData;

import java.util.List;

public interface OperationsInterface {
    public void insert(User user);

    public void delete(User user);

    public void update(User user);

    public void insert(Transaction transaction);

    public void delete(Transaction transaction);

    public void update(Transaction transaction);

    public void insert(ReferenceKey referenceKey);

    public void delete(ReferenceKey referenceKey);

    public void update(ReferenceKey referenceKey);

    LiveData<List<Transaction>> getTransactionByUser(int UserKey);

    LiveData<List<ReferenceKey>> getReferenceByUserKey(int UserKey);

    LiveData<List<ReferenceKey>> getReferenceByCode(String code);

}
